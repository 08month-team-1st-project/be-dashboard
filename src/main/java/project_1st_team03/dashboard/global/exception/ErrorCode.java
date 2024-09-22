package project_1st_team03.dashboard.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    private final HttpStatus httpStatus;
    private final String message;
}
