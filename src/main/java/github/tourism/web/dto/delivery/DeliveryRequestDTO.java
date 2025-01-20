package github.tourism.web.dto.delivery;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryRequestDTO {
    private String mainAddress;
    private String detailAddress;
    private String zipCode;
    private String receiverName;
    private String receiverPhone;
}
