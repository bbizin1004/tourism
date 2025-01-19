package github.tourism.service.payment;


import github.tourism.data.entity.goods.Goods;
import github.tourism.data.entity.order.Order;
import github.tourism.data.entity.order.OrderItems;
import github.tourism.data.entity.payment.Payment;
import github.tourism.data.repository.cart.CartRepository;
import github.tourism.data.repository.goods.GoodsRepository;
import github.tourism.data.repository.order.OrderRepository;
import github.tourism.data.repository.payment.PaymentRepository;
import github.tourism.service.payment.imp.IMPService;
import github.tourism.web.dto.payment.*;
import github.tourism.web.exception.PaymentProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final IMPService impService;
    private final OrderRepository orderRepository;

    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO) {
        Integer orderId = paymentRequestDTO.getOrderId();

        // 1. 주문 정보 가져오기
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        // 2. 총 결제 금액 확인
        // BigDecimal -> 금액 계산시 오류 피하기 위해 사용 ! 정밀도가 높음
        BigDecimal totalAmount = order.calculateTotalPrice();

        // 결제 금액 확인
        if (totalAmount.compareTo(paymentRequestDTO.getTotalAmount()) != 0) {
            throw new IllegalArgumentException("결제 금액이 일치하지 않습니다.");
        }

        // 3. 포트원 결제 검증
        PaymentVerificationResponse verificationResponse = impService.verifyPayment(paymentRequestDTO,totalAmount);
        if (!verificationResponse.isVerified()) {
            throw new PaymentProcessingException(verificationResponse.getMessage());
        }

        // 4. 재고 감소 (비관적 락 사용)
        reduceStock(order);

        // 5. 결제 정보 저장
        Payment payment = savePayment(paymentRequestDTO,order);

        // 6. 장바구니 비우기 -> 사용자가 선택한 상품이 지워지도록 설정해야함
        deleteCartItemsFromOrder(order);

        return new PaymentResponseDTO(paymentRequestDTO.getImpUid(),totalAmount,"결제 완료");

    }

    // 장바구니에서 결제한 상품만 삭제
    private void deleteCartItemsFromOrder(Order order) {
        List<OrderItems> orderItems = order.getOrderItems();

        // 주문에 포함된 상품들의 Id 반환
        List<Integer> orderItemGoodsIds = orderItems.stream()
                .map(orderItem -> orderItem.getGoods().getGoodId())
                .collect(Collectors.toList());

        // 해당 상품들이 포함된 장바구니 항목 삭제
        cartRepository.deleteByGoodsIdIn(orderItemGoodsIds);
    }


    // 재고 감소
    private void reduceStock(Order order) {
        order.getOrderItems().forEach(orderItem -> {
            Goods goods = goodsRepository.findByIdForUpdate(orderItem.getGoods().getGoodId())
                    .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

            if (goods.getStockQuantity() < orderItem.getQuantity()) {
                throw new IllegalArgumentException("재고 부족 : " + goods.getGoodsName());
            }

            goods.setStockQuantity(goods.getStockQuantity() - orderItem.getQuantity());
            goodsRepository.save(goods);
        });
    }

    // 결제 정보 저장
    private Payment savePayment(PaymentRequestDTO paymentRequestDTO, Order order) {
        Payment payment = Payment.from(order, paymentRequestDTO.getImpUid(), paymentRequestDTO.getMerchantUid(),
                PaymentStatus.COMPLETE, paymentRequestDTO.getPaymentCard());
        return paymentRepository.save(payment);
    }


    // 결제 내역 조회
    @Transactional(readOnly = true)
    public List<PaymentHistoryResponseDTO> getPaymentHistory(Integer userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);

        if (payments.isEmpty()) {
            throw new IllegalArgumentException("결제 내역이 없습니다.");
        }

        return payments.stream().map(payment -> {
            Order order = payment.getOrder();
            String itemSummary = order.getOrderItems().get(0).getGoods().getGoodsName() + " 외 "
                    + (order.getOrderItems().size()-1) + "건";

            List<OrderItemDetailDTO> orderItemDetails = order.getOrderItems().stream().map(orderItem ->
                    new OrderItemDetailDTO(
                            orderItem.getGoods().getGoodsImage(),
                            orderItem.getGoods().getGoodsName(),
                            orderItem.getQuantity(),
                            orderItem.getPrice()
                    )
            ).collect(Collectors.toList());

            return new PaymentHistoryResponseDTO(
                    payment.getPaymentId(),
                    payment.getPaymentDate().toLocalDateTime(),
                    order.getUser().getUserName(),
                    order.getDeliveryInfo().getMainAddress() + " " + order.getDeliveryInfo().getDetailAddress() + " " + order.getDeliveryInfo().getZipCode(),
                    order.getDeliveryInfo().getReceiverPhone(),
                    itemSummary,
                    orderItemDetails,
                    order.calculateTotalPrice(),
                    payment.getTotalPrice()
            );
        }).collect(Collectors.toList());
    }
}

