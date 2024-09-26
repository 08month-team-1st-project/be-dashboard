package project_1st_team03.dashboard.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project_1st_team03.dashboard.domain.comment.exception.CommentException;
import project_1st_team03.dashboard.domain.member.exception.MemberException;
import project_1st_team03.dashboard.domain.post.exception.PostException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleRuntimeException(Exception e) {
        log.error("[Exception] ex", e);
        return ResponseEntity.internalServerError().body("서버에 문제가 발생했습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[MethodArgumentNotValidException] {}", FieldErrorCustom.getFieldErrorList(e.getFieldErrors()));
        return ResponseEntity.badRequest().body(new ErrorResult(e));
    }

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResult> handleMemberException(MemberException e) {
        log.error("[MemberException] message= {}", e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new ErrorResult(errorCode));
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<ErrorResult> handlePostException(PostException e) {
        log.error("[PostException] message= {}", e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new ErrorResult(errorCode));
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorResult> handlePostException(CommentException e) {
        log.error("[CommentException] message= {}", e.getMessage());

        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new ErrorResult(errorCode));
    }

}
