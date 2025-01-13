package github.tourism.web.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartListResponse {

    private Integer goodId;
    private String goodName;
    private String goodImage;
    private Integer goodPrice;
    private Integer goodQuantity;

    public CartListResponse(Integer goodId, String goodName, String goodImage, Integer goodPrice, Integer goodQuantity) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodImage = goodImage;
        this.goodPrice = goodPrice;
        this.goodQuantity = goodQuantity;
    }
}
