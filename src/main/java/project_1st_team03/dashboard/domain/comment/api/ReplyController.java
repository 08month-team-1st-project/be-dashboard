package project_1st_team03.dashboard.domain.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.application.ReplyService;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.comment.dto.ReplyRequest;
import project_1st_team03.dashboard.domain.comment.dto.ReplyResponse;
import project_1st_team03.dashboard.global.security.MemberDetails;

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
    public ResponseEntity<String> createReply(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @RequestBody ReplyRequest request) {

        replyService.createComments(memberDetails,request);
        return ResponseEntity.ok("답글이 성공적으로 작성되었습니다.");
    }

    @GetMapping("/replies")
    public ResponseEntity<Map<String,Object>> getAllReplies() {
        List<ReplyResponse> replyResponses = replyService.getAllComment();
        //comments 필드로 응답.
        Map<String, Object> response = new HashMap<>();
        response.put("reply", replyResponses);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/replies/{replies_id}")
    public ResponseEntity<String> deleteReplyByPathId(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("replies_id") Long id){
        replyService.deleteComment(memberDetails,id);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
//
    @PutMapping("/replies/{replies_id}")
    public ResponseEntity<String> updateReply(
    @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("replies_id") Long id,
            @RequestBody ReplyRequest ReplyRequest){

        replyService.updateComment(memberDetails,id,ReplyRequest);
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }
}
