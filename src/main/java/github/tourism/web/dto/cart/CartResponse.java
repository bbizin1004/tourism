package github.tourism.web.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartResponse {

    private String message;
    private Integer cartId;

    public CartResponse(String message, Integer cartId) {
        this.message = message;
        this.cartId = cartId;
    }


}
