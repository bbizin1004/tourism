package github.tourism.service.payment.order;

import github.tourism.data.entity.cart.Cart;
import github.tourism.data.entity.delivery.Delivery;
import github.tourism.data.entity.goods.Goods;
import github.tourism.data.entity.order.Order;
import github.tourism.data.entity.order.OrderItems;
import github.tourism.data.entity.user.User;
import github.tourism.data.repository.cart.CartRepository;
import github.tourism.data.repository.delivery.DeliveryRepository;
import github.tourism.data.repository.order.OrderItemRepository;
import github.tourism.data.repository.order.OrderRepository;
import github.tourism.data.repository.user.UserRepository;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.delivery.DeliveryRequestDTO;
import github.tourism.web.dto.order.OrderStatus;
import github.tourism.web.exception.BadRequestException;
import github.tourism.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;


    @Transactional
    public Integer createOrder(Integer userId, List<Integer> cartItemIds, DeliveryRequestDTO deliveryRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUNDED));

        // 1. 사용자가 선택한  장바구니 상품 조회
        List<Cart> selectedCartItems = cartRepository.findByCartIdIn(cartItemIds);
        if (selectedCartItems.isEmpty()) {
            throw new BadRequestException(ErrorCode.CART_EMPTY);
        }

        // 2. 배송정보 생성
        Delivery deliveryInfo = findOrCreateDeliveryInfo(deliveryRequestDTO,user);

        // 3. 주문 생성
        Order order = Order.builder()
                .user(user)
                .deliveryInfo(deliveryInfo)
                .status(OrderStatus.CREATED)
                .build();
        Order saveOrder = orderRepository.save(order);

        // 4. 주문 항목 생성 및 저장
        List<OrderItems> orderItems = selectedCartItems.stream()
                        .map(cartItem -> OrderItems.from(saveOrder,cartItem))
                                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);  // 주문 항목 저장

        return saveOrder.getOrderId();
    }

    // 배송 객체 생성
    private Delivery findOrCreateDeliveryInfo(DeliveryRequestDTO dto, User user) {
        return deliveryRepository.findByMainAddressAndUser(dto.getMainAddress(),user)
                .orElseGet(() -> {
                    Delivery newInfo = new Delivery();
                    newInfo.setMainAddress(dto.getMainAddress());
                    newInfo.setDetailAddress(dto.getDetailAddress());
                    newInfo.setZipCode(dto.getZipCode());
                    newInfo.setReceiverName(dto.getReceiverName());
                    newInfo.setReceiverPhone(dto.getReceiverPhone());
                    newInfo.setUser(user);
                    return  deliveryRepository.save(newInfo);
                });

    }
}

