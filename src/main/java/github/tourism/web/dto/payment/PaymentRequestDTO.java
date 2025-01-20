package github.tourism.web.dto.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentRequestDTO {
    private Integer userId;
    private Integer orderId;
    private String impUid;         // 결제 고유 ID (포트원에서 발급)
    private String merchantUid;    // 상점 고유 ID (주문 고유 ID)
    private String paymentCard;    // 결제 카드 정보 (선택 사항, 카드 결제 방식에 따라 다름)
    private BigDecimal totalAmount;     // 결제 금액
    private String paymentMethod;  // 결제 방법 (카드)
}
