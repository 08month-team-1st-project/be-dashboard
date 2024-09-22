package project_1st_team03.dashboard.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // PostException
    NOT_FOUND_POST(NOT_FOUND, "존재하지 않는 게시글입니다."),
    UNAUTHORIZED_EDIT_POST_ATTEMPT(FORBIDDEN, "본인이 작성하지 않은 게시글은 수정할 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
