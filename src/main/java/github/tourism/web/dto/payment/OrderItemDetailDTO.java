package github.tourism.web.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDetailDTO {
    private String itemImage;
    private String itemName;
    private Integer quantity;
    private BigDecimal price;
}
