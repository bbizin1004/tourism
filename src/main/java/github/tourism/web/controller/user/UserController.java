package github.tourism.web.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.tourism.service.user.UserService;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.user.sign.*;
import github.tourism.web.exception.BadRequestException;
import github.tourism.web.exception.NotAcceptException;
import github.tourism.web.exception.NotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService authService;

    @PostMapping(value = "/email")
    public ResponseEntity<CheckedEmailResponse> checkEmail(@Valid @RequestBody CheckedEmailRequest emailCheckRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(ErrorCode.REGISTER_FAILURE);
        }

        boolean validation = authService.checkedEmail(emailCheckRequest);
        return ResponseEntity.ok(validation ? new CheckedEmailResponse(true,"중복되지 않은 이메일입니다."):new CheckedEmailResponse(false,"이미 사용중인 이메일입니다."));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<SignResponse> register(@Valid@RequestBody String signUpRequestJson) {
        SignRequest signUpRequest;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            signUpRequest = objectMapper.readValue(signUpRequestJson, SignRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new SignResponse(false, "잘못된 요청 형식입니다."));
        }
        boolean isSuccess = authService.signUp(signUpRequest);
        return ResponseEntity.ok(isSuccess ? new SignResponse(true,"회원가입 성공하였습니다.") : new SignResponse(false,"회원가입 실패하였습니다."));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    log.error("Field: {}, Message: {}", error.getField(), error.getDefaultMessage())
            );
            throw new BadRequestException(ErrorCode.REGISTER_FAILURE);
        }

        Map<String, String> tokens = authService.login(loginRequest);
        httpServletResponse.setHeader("Authorization", "Bearer " + tokens.get("access"));
        Cookie refreshCookie = createCookie("refresh", tokens.get("refresh"));
        httpServletResponse.addCookie(refreshCookie);
        return ResponseEntity.ok(tokens);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(3*60*60); // 쿠키의 유효 기간을 설정
        cookie.setSecure(true); // 쿠키가 HTTPS 연결을 통해서만 전송되도록 설정
        cookie.setPath("/"); // 쿠키가 유효한 경로를 설정
        cookie.setHttpOnly(true); //쿠키를 HTTP 전용으로 설정 -> JavaScript와 같은 클라이언트 측 스크립트에서 이 쿠키에 접근할 수 없게 됩니다.

        return cookie;
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<DeletedUserResponse> secession() {
        try {
            boolean successSecession = authService.secession();
            return ResponseEntity.ok(new DeletedUserResponse(successSecession,"회원 탈퇴 과정이 완료되었습니다."));
        } catch (NotAcceptException e) {
            throw new NotAcceptException(ErrorCode.USER_SECESSION_FAILURE);
        } catch (NotFoundException e) {
            throw new NotFoundException(ErrorCode.SECESSION_NOT_FOUND);
        }
    }
}
