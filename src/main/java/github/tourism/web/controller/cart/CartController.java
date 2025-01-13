package github.tourism.web.controller.cart;

import github.tourism.service.cart.CartService;
import github.tourism.service.user.security.CustomUserDetails;
import github.tourism.web.dto.cart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 담기
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        request.setUserId(userDetails.getUserId());
        Integer cartId = cartService.addToCart(request);
        CartResponse cartResponse = new CartResponse("상품이 장바구니에 추가되었습니다.",  cartId);
        return ResponseEntity.ok(cartResponse);
    }

    // 장바구니 전체 조회
    @GetMapping("/check")
    public ResponseEntity<List<CartListResponse>> getCart(@AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<CartListResponse> cartList = cartService.getCartByUserId(userDetails.getUserId());
        return cartList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cartList);
    }

    // 장바구니 수량 조절
    @PostMapping("/update")
    public ResponseEntity<CartResponseWrapper> updateCart(@RequestBody CartUpdateRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CartResponseWrapper updateCart = cartService.updateCart(request.getCartId(), request.getQuantity());
        if ("Error".equals(updateCart.getStatus()) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updateCart);
        }
        return ResponseEntity.ok(updateCart);
    }

    // 장바구니 목록 삭제 (단건)
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<CartResponse> deleteCart(@PathVariable Integer cartId, @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer deleteId = cartService.deleteCart(cartId);
        CartResponse cartResponse = new CartResponse("해당 굿즈가 삭제되었습니다.", deleteId);
        return ResponseEntity.ok(cartResponse);
    }
}

