package project_1st_team03.dashboard.domain.member.exception;

import lombok.Getter;
import project_1st_team03.dashboard.global.exception.ErrorCode;

@Getter
public class MemberException extends RuntimeException {

    private final ErrorCode errorCode;
    public MemberException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}