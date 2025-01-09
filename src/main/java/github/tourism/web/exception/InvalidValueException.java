package github.tourism.web.exception;

import github.tourism.web.advice.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class InvalidValueException extends RuntimeException {
    private final HttpStatus httpStatus;
    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}
