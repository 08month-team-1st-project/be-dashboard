package project_1st_team03.dashboard.domain.post.exception;

import lombok.Getter;
import project_1st_team03.dashboard.global.exception.ErrorCode;

@Getter
public class PostException extends RuntimeException {

    private final ErrorCode errorCode;

    public PostException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
