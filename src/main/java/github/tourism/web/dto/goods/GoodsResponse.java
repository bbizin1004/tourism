package github.tourism.web.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoodsResponse {

    private int goodsId;
    private String goodsImage;
    private String goodsName;
    private Integer goodsPrice;
    private String goodsDescription;


}
