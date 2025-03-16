package github.tourism.web.dto.cart;

import github.tourism.data.entity.cart.Cart;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartListResponse {

    private Integer cartId;
    private Integer goodId;
    private String goodName;
    private String goodImage;
    private Integer goodPrice;
    private Integer goodQuantity;

    public CartListResponse(Integer cartId,Integer goodId, String goodName, String goodImage, Integer goodPrice, Integer goodQuantity) {
        this.cartId = cartId;
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodImage = goodImage;
        this.goodPrice = goodPrice;
        this.goodQuantity = goodQuantity;
    }

    public static CartListResponse from (Cart cart) {
        return new CartListResponse(
                cart.getCartId(),
                cart.getGoods().getGoodId(),
                cart.getGoods().getGoodsName(),
                cart.getGoods().getGoodsImage(),
                cart.getGoods().getPrice(),
                cart.getQuantity()
        );
    }
}
