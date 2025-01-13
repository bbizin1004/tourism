package github.tourism.web.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartUpdateRequest {

    private Integer cartId;
    private Integer quantity;

    public CartUpdateRequest(Integer cartId, Integer quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }
}
