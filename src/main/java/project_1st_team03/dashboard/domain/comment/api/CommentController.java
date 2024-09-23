package project_1st_team03.dashboard.domain.comment.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;


@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(@RequestBody CommentsRequest request) {
        commentService.createComments(request);
        return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
    }
}
