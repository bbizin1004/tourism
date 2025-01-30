package github.tourism.web.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus {
    _OK(200,"성공적으로 실행되었습니다.",HttpStatus.OK);
    private final int statusCode;
    private final String message;
    private final HttpStatus httpStatus;
}
