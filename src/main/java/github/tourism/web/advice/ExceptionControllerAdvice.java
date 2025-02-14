package github.tourism.web.advice;

import github.tourism.web.exception.*;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // CustomValidationException 처리
    public ResponseEntity<ApiResponse<Object>> handleValidationException(CustomValidationException e, WebRequest request) {
        log.warn("Validation Error: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.onFailure(400, e.getMessage(), null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElse(ErrorCode.REGISTER_FAILURE.getErrorMessage());

        return ResponseEntity.badRequest().body(ApiResponse.onFailure(ErrorCode.REGISTER_FAILURE.getStatusCode(), errorMessage, null));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse(ErrorCode.REGISTER_FAILURE.getErrorMessage());
            errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
        });

        return ResponseEntity.badRequest().body(ApiResponse.onFailure(ErrorCode.REGISTER_FAILURE.getStatusCode(), "잘못된 요청 형식입니다.", errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(BadRequestException e, WebRequest request) {
        log.warn("Bad Request: {}", e.getMessage());
        return ResponseEntity.badRequest().body(ApiResponse.onFailure(ErrorCode.EMAIL_ALREADY_EXIST.getStatusCode(), ErrorCode.EMAIL_ALREADY_EXIST.getErrorMessage(), null));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException e, WebRequest request) {
        log.warn("Not Found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.onFailure(ErrorCode.EMAIL_NOT_EXIST.getStatusCode(), ErrorCode.EMAIL_NOT_EXIST.getErrorMessage(), null));
    }

    @ExceptionHandler(NotAcceptException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotAcceptException(NotAcceptException e, WebRequest request) {
        log.warn("Not Accept: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(ApiResponse.onFailure(ErrorCode.LOGIN_FAILURE.getStatusCode(), ErrorCode.LOGIN_FAILURE.getErrorMessage(), null));
    }

    @ExceptionHandler(DeletedUserException.class)
    public ResponseEntity<ApiResponse<Object>> handleDeletedUserException(DeletedUserException e, WebRequest request) {
        log.warn("Deleted User: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.GONE) // 410 Gone
                .body(ApiResponse.onFailure(ErrorCode.SECESSION_DETAIL.getStatusCode(), ErrorCode.SECESSION_DETAIL.getErrorMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception e, WebRequest request) {
        log.error("예외 발생: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure(500, "서버 오류가 발생했습니다.", e.getMessage()));
    }
}

