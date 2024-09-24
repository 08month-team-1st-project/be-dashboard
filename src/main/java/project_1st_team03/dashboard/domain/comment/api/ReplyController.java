package project_1st_team03.dashboard.domain.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.application.ReplyService;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.comment.dto.ReplyRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/replies")
    public ResponseEntity<String> createComment(
            @RequestBody ReplyRequest request) {

        replyService.createComments(request);
        return ResponseEntity.ok("답글이 성공적으로 작성되었습니다.");
    }

//    @GetMapping("/comments")
//    public ResponseEntity<Map<String,Object>> getAllComments() {
//        List<CommentsResponse> comments = commentService.getAllComment();
//        //comments 필드로 응답.
//        Map<String, Object> response = new HashMap<>();
//        response.put("comments", comments);
//
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/comments/{comment_id}")
//    public ResponseEntity<String> deleteCommentByPathId(
//            @PathVariable("comment_id") Long id){
//        replyService.deleteComment(id);
//        return ResponseEntity.ok("댓글이 삭제되었습니다.");
//    }
//
//    @PutMapping("/comments/{comment_id}")
//    public ResponseEntity<String> updateComment(
//            @PathVariable("comment_id") Long id,
//            @RequestBody CommentsRequest commentRequest){
//
//        replyService.updateComment(id,commentRequest);
//        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
//    }
}
