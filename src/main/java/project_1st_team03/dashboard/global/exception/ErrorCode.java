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
    ALREADY_REGISTERED_EMAIL(CONFLICT,"이미 등록된 이메일 입니다."),
    NOT_CORRECT_ACCOUNT_ID_OR_PASSWORD(UNAUTHORIZED,"아이디 또는 비밀번호를 잘못 입력했습니다."),

    // PostException
    NOT_FOUND_POST(NOT_FOUND, "존재하지 않는 게시글입니다."),
    UNAUTHORIZED_EDIT_POST_ATTEMPT(FORBIDDEN, "본인이 작성하지 않은 게시글은 수정할 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
