package project_1st_team03.dashboard.domain.member.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class SignupRequest {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,20}$", message = "비밀번호는 8~20자 영문 대소문자, 숫자, 특수문자를 사용해야 합니다.")
    private String password;
}
