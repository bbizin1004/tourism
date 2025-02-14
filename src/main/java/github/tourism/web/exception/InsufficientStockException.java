package github.tourism.web.exception;

import github.tourism.web.advice.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InsufficientStockException extends IllegalArgumentException {
  private final HttpStatus httpStatus;
  public InsufficientStockException(ErrorCode errorCode) {
    super(errorCode.getErrorMessage());
    this.httpStatus = errorCode.getHttpStatus();
  }
}