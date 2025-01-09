package github.tourism.web.exception;

import github.tourism.web.advice.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QuantityExceededException extends RuntimeException{
    private final HttpStatus httpStatus;
    public QuantityExceededException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}
