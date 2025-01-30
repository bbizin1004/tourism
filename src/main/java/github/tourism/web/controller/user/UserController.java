package github.tourism.web.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.tourism.config.util.JwtAuthorization;
import github.tourism.service.user.UserService;
import github.tourism.web.advice.ApiResponse;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.user.UserInfoDTO;
import github.tourism.web.dto.user.sign.*;
import github.tourism.web.exception.BadRequestException;
import github.tourism.web.exception.CustomValidationException;
import github.tourism.web.exception.NotAcceptException;
import github.tourism.web.exception.NotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<CheckedEmailResponse>> checkEmail(@Valid @RequestBody CheckedEmailRequest emailCheckRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(ErrorCode.REGISTER_FAILURE);
        }

        boolean validation = authService.checkedEmail(emailCheckRequest);
        CheckedEmailResponse response = new CheckedEmailResponse(validation, validation ? "중복되지 않은 이메일입니다." : "이미 사용중인 이메일입니다.");
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<ApiResponse<SignResponse>> register(@Valid @RequestBody String signUpRequestJson) {
        SignRequest signUpRequest;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            signUpRequest = objectMapper.readValue(signUpRequestJson, SignRequest.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(ApiResponse.onFailure(400, "잘못된 요청 형식입니다.", null));
        }

        boolean isSuccess = authService.signUp(signUpRequest);
        SignResponse response = new SignResponse(isSuccess, isSuccess ? "회원가입 성공하였습니다." : "회원가입 실패하였습니다.");
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(ErrorCode.REGISTER_FAILURE);
        }

        Map<String, String> tokens = authService.login(loginRequest);
        httpServletResponse.setHeader("Authorization", "Bearer " + tokens.get("access"));
        Cookie refreshCookie = createCookie("refresh", tokens.get("refresh"));
        httpServletResponse.addCookie(refreshCookie);
        return ResponseEntity.ok(ApiResponse.onSuccess(tokens));
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<ApiResponse<DeletedUserResponse>> secession(@JwtAuthorization UserInfoDTO userInfo) {
        try {
            String email = userInfo.getEmail();

            boolean successSecession = authService.secession(email);
            DeletedUserResponse response = new DeletedUserResponse(successSecession, "회원 탈퇴 과정이 완료되었습니다.");
            return ResponseEntity.ok(ApiResponse.onSuccess(response));
        } catch (NotAcceptException e) {
            log.warn("탈퇴 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ApiResponse.onFailure(ErrorCode.USER_SECESSION_FAILURE.getStatusCode(), ErrorCode.USER_SECESSION_FAILURE.getErrorMessage(), null));
        } catch (NotFoundException e) {
            log.warn("사용자 찾기 실패: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.onFailure(ErrorCode.USER_NOT_FOUNDED.getStatusCode(), ErrorCode.USER_NOT_FOUNDED.getErrorMessage(), null));
        }
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(3 * 60 * 60); // 쿠키의 유효 기간을 설정
        cookie.setSecure(true); // 쿠키가 HTTPS 연결을 통해서만 전송되도록 설정
        cookie.setPath("/"); // 쿠키가 유효한 경로를 설정
        cookie.setHttpOnly(true); // 쿠키를 HTTP 전용으로 설정
        return cookie;
    }
}
