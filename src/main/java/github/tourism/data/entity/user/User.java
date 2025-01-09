package github.tourism.data.entity.user;

import github.tourism.web.advice.ErrorCode;
import github.tourism.web.dto.user.sign.Authority;
import github.tourism.web.exception.InvalidValueException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth2")
@DynamicInsert
@Builder
@ToString
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;

    @Column(name = "user_name", nullable = false)
    @NotEmpty(message = "사용자 이름은 필수입니다.")
    private String userName;

    @Column(name = "country", nullable = false)
    @NotEmpty(message = "국가 선택은 필수입니다.")
    private String country;

    @Column(name = "gender", nullable = false)
    @NotEmpty(message = "성별 선택은 필수입니다.")
    private String gender;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @Column(name = "delete_at")
    private LocalDateTime deletedAt;

    @Builder.Default
    @Transient
    private Set<Authority> authorities = Set.of(Authority.ROLE_USER);

    public void User(Integer userId) {
    }


    public void deleteUser() {
        this.deletedAt = LocalDateTime.now();
    }

    //유저 정보 수정 책임분리 추가 ==============

    public void updateUserName(String userName) {
        if (userName != null && !userName.isEmpty()) {
            if(userName.length() <= 50){
                this.userName = userName;
            }else{
                throw new InvalidValueException(ErrorCode.FAILURE_USER_NAME);
            }
        }
    }

    public void updateCountry(String country) {
        if (country != null && !country.isEmpty()) {
            if(country.length() <= 50){
                this.country = country;
            }else{
                throw new InvalidValueException(ErrorCode.FAILURE_USER_NAME);
            }
        }
    }

    public void updateGender(String gender) {
        if (gender != null  && !gender.isEmpty()) {
            if(gender.length() == 1){
                this.gender = gender;
            }else{
                throw new InvalidValueException(ErrorCode.FAILURE_GENDER);
            }
        }
    }

    //유저 정보 수정 책임분리 추가 ==============

}