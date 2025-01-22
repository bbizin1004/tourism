package github.tourism.web.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class cancelRequestDTO {
    private String reason;  // 취소 사유

}
