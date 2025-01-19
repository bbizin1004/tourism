package github.tourism.web.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentVerificationResponse {
    private boolean verified;
    private String message;
}
