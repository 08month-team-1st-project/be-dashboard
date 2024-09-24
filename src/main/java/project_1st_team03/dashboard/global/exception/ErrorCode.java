package project_1st_team03.dashboard.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //MemberException
    NOT_FOUND_MEMBER(NOT_FOUND, "존재하지 않는 회원입니다."),
    ALREADY_REGISTERED_EMAIL(CONFLICT,"이미 등록된 이메일 입니다.")
    ;
    private final HttpStatus httpStatus;
    private final String message;
}
