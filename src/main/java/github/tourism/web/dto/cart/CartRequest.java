package github.tourism.web.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartRequest {

    private Integer userId;
    private Integer goodId;
    private Integer quantity;

    public CartRequest(Integer userId, Integer goodId, Integer quantity) {
        this.userId = userId;
        this.goodId = goodId;
        this.quantity = quantity;
    }
}
