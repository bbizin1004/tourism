package github.tourism.web.controller.order;

import github.tourism.service.payment.order.OrderService;
import github.tourism.service.user.security.CustomUserDetails;
import github.tourism.web.dto.order.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성 (cartId 를 바탕으로 order 생성)
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                     @RequestBody List<Integer> cartItemIds) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Integer orderId =  orderService.createOrder(userDetails.getUserId(),cartItemIds);
        OrderResponse orderResponse = new OrderResponse(orderId,"주문이 완료되었습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

}