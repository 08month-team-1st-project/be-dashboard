package project_1st_team03.dashboard.domain.comment.exception;

import lombok.Getter;
import project_1st_team03.dashboard.global.exception.ErrorCode;

@Getter
public class CommentException extends RuntimeException {

    private final ErrorCode errorCode;

    public CommentException( ErrorCode errorCode) {this.errorCode = errorCode;}
}


