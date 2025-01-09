package github.tourism.service.user;

import github.tourism.config.security.JwtTokenProvider;
import github.tourism.data.entity.user.User;
import github.tourism.data.repository.user.UserRepository;
import github.tourism.service.s3.S3Service;
import github.tourism.service.user.security.CustomUserDetails;
import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.user.sign.Authority;
import github.tourism.web.dto.user.sign.CheckedEmailRequest;
import github.tourism.web.dto.user.sign.LoginRequest;
import github.tourism.web.dto.user.sign.SignRequest;
import github.tourism.web.exception.BadRequestException;
import github.tourism.web.exception.DeletedUserException;
import github.tourism.web.exception.NotAcceptException;
import github.tourism.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final S3Service awsS3Service;

    private boolean isPasswordStrong(String password) {
        return password.length() >= 8;
    }

    private void checkUserDeleted(User user) {
        if (user.getDeletedAt() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = user.getDeletedAt().format(formatter);
            throw new DeletedUserException(formattedDate, ErrorCode.SECESSION_DETAIL);
        }
    }

    public boolean checkedEmail(CheckedEmailRequest checkedEmailRequest){
        String email = checkedEmailRequest.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("이메일이 이미 존재합니다: {}", email);
            throw new BadRequestException(ErrorCode.EMAIL_ALREADY_EXIST);
        }
        return true;
    }

    @Transactional(transactionManager = "tmJpa1")
    public boolean signUp(SignRequest signUpRequest) {
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String username = signUpRequest.getUserName();
        String country = signUpRequest.getCountry();
        String gender = signUpRequest.getGender();



        if (!isPasswordStrong(password)) {
            log.warn("비밀번호가 너무 약합니다.");
            throw new BadRequestException(ErrorCode.WEAK_PASSWORD);
        }

        User userPrincipal = User.builder()
                .email(email)
                .userName(username)
                .password(passwordEncoder.encode(password))
                .country(country)
                .gender(gender)
                .authorities(new HashSet<>(List.of(Authority.ROLE_USER)))
                .build();

        userRepository.save(userPrincipal);
        return true;
    }

    public String login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.EMAIL_NOT_EXIST));

        checkUserDeleted(user);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

            Set<Authority> roles = ((CustomUserDetails) userPrincipal).getAuthoritySet();

            return jwtTokenProvider.createToken(email, userPrincipal.getUsername(), roles);

        } catch (BadCredentialsException e) {
            log.warn("로그인 실패: 잘못된 비밀번호");
            throw new BadRequestException(ErrorCode.LOGIN_FAILURE3);
        } catch (Exception e) {
            log.error("로그인 처리 중 오류 발생: {}", e.getMessage());
            throw new NotAcceptException(ErrorCode.LOGIN_FAILURE);
        }
    }

    @Transactional(transactionManager = "tmJpa1")
    public boolean secession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Integer userId = userDetails.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUNDED));

        if (user.getDeletedAt() != null) {
            throw new NotAcceptException(ErrorCode.USER_SECESSION_FAILURE);
        }

        user.deleteUser();
        userRepository.save(user);

        return true;
    }
}
