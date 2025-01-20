package github.tourism.web.exception;

import github.tourism.web.advice.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
public class CustomValidationException extends RuntimeException {
    private final HttpStatus httpStatus;
    public CustomValidationException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}
