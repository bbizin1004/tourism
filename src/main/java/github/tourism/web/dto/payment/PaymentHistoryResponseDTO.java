package github.tourism.web.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistoryResponseDTO {
    private Integer paymentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime purchaseDate;

    private String userName;
    private String address;
    private String phone;
    private String itemSummary;
    private List<OrderItemDetailDTO> orderItems;
    private BigDecimal totalAmount;
    private BigDecimal paymentAmount;
}
