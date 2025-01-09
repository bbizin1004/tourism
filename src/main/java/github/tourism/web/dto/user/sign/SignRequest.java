package github.tourism.web.dto.user.sign;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
public class SignRequest {
    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
            message = "비밀번호는 영문자, 숫자 및 특수 기호를 모두 포함해야 합니다.")
    private String password;
    @NotEmpty(message = "사용자 이름은 필수입니다.")
    private String userName;
    @NotEmpty(message = "국가 선택은 필수입니다.")
    private String country;
    @NotEmpty(message = "성별 선택은 필수입니다.")
    private String gender;

}
