package github.tourism.web.dto.order;

import github.tourism.web.dto.delivery.DeliveryRequestDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequestDTO {
    private List<Integer> cartItemIds;
    private DeliveryRequestDTO deliveryInfo;
}
