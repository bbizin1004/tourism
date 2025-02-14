package github.tourism.web.dto.user.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private HttpStatus httpStatus;

    private boolean isSuccess;
    private int statusCode;
    private String message;
    public LoginResponse(boolean isSuccess, String message) {
        this.httpStatus = isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST; // 기본 HTTP 상태 설정
        this.isSuccess = isSuccess;
        this.statusCode = isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }
}
