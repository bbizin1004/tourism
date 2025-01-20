package github.tourism.web.advice;


import github.tourism.web.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResult> handleNotFoundException(NotFoundException nfe){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(nfe.getHttpStatus().value());
        er.setStatus(nfe.getHttpStatus());
        er.setMessage(nfe.getMessage());
        log.error(nfe.getMessage());

        return ResponseEntity.status(nfe.getHttpStatus()).body(er);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptException.class)
    public ResponseEntity<ErrorResult> notAcceptException(NotAcceptException e) {
        ErrorResult er = new ErrorResult();
        er.setStatusCode(e.getHttpStatus().value());
        er.setStatus(e.getHttpStatus());
        er.setMessage(e.getMessage());
        log.error(e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(er);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidValueException.class)
    public String handleInvalidValueException(InvalidValueException ive){
        log.error("Client 요청에 문제가 있습니다. - " + ive.getMessage());
        return ive.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ade){
        log.error("Client 요청에 문제가 있어 다음처럼 출력합니다. " + ade.getMessage());
        return ade.getMessage();
    }

    @ExceptionHandler(CAccessDeniedException.class)
    public ResponseEntity<ErrorResult> handleAccessDeniedException(CAccessDeniedException ade){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(ade.getHttpStatus().value());
        er.setStatus(ade.getHttpStatus());
        er.setMessage(ade.getMessage());
        log.error(ade.getMessage());

        return ResponseEntity.status(ade.getHttpStatus()).body(er);
    }
    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public ResponseEntity<ErrorResult> handleAuthenticationException(CAuthenticationEntryPointException ae){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(ae.getHttpStatus().value());
        er.setStatus(ae.getHttpStatus());
        er.setMessage(ae.getMessage());
        log.error(ae.getMessage());

        return ResponseEntity.status(ae.getHttpStatus()).body(er);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResult> handleBadRequestException(BadRequestException bre){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(bre.getHttpStatus().value());
        er.setStatus(bre.getHttpStatus());
        er.setMessage(bre.getMessage());
        log.error(bre.getMessage());

        return ResponseEntity.status(bre.getHttpStatus()).body(er);
    }
    @ExceptionHandler(DeletedUserException.class)
    public ResponseEntity<ErrorResult> handleDeletedUserException(DeletedUserException due){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(due.getHttpStatus().value());
        er.setStatus(due.getHttpStatus());
        er.setMessage(due.getMessage());
        log.error(due.getMessage());

        return ResponseEntity.status(due.getHttpStatus()).body(er);
    }
    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResult> handleExpiredTokenException(ExpiredTokenException ete){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(ete.getHttpStatus().value());
        er.setStatus(ete.getHttpStatus());
        er.setMessage(ete.getMessage());
        log.error(ete.getMessage());

        return ResponseEntity.status(ete.getHttpStatus()).body(er);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResult> handleUnauthorizedException(UnauthorizedException uae){
        ErrorResult er = new ErrorResult();
        er.setStatusCode(uae.getHttpStatus().value());
        er.setStatus(uae.getHttpStatus());
        er.setMessage(uae.getMessage());
        log.error(uae.getMessage());

        return ResponseEntity.status(uae.getHttpStatus()).body(er);
    }
}
