package github.tourism.web.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentCancelResponseDTO {
    private String status;
    private String message;
    private String impUid;
    private BigDecimal cancelledAmount;

}
