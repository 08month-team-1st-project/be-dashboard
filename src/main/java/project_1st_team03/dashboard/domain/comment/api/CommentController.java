package project_1st_team03.dashboard.domain.comment.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;

import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<String> createComment(
            @RequestBody CommentsRequest request) {

        commentService.createComments(request);
        return ResponseEntity.ok("댓글이 성공적으로 작성되었습니다.");
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentsResponse>> getAllComments() {
        List<CommentsResponse> comments = commentService.getAllComment();
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/comments/:comment_id")
    public ResponseEntity<String> deleteCommentByPathId(
            @PathVariable("comment_id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }

    @PutMapping("/comments/:comment_id")
    public ResponseEntity<String> updateComment(
            @PathVariable("comment_id") Long id,
            @RequestBody CommentsRequest commentRequest){

        commentService.updateComment(id,commentRequest);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }
}
