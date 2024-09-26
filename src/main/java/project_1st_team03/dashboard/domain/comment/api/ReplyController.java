package project_1st_team03.dashboard.domain.comment.api;

import jakarta.validation.Valid;
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
    public ResponseEntity<Void> createReply(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @Valid @RequestBody ReplyRequest request) {

        replyService.createComments(memberDetails,request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/replies")
    public ResponseEntity<Map<String,Object>> getRepliesById(
            @RequestParam("comment_id") Long commentId) {
        List<ReplyResponse> replyResponses = replyService.getReplyById(commentId);
        Map<String, Object> response = new HashMap<>();
        response.put("reply", replyResponses);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/replies/{replies_id}")
    public ResponseEntity<Void> deleteReplyByPathId(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("replies_id") Long id){
        replyService.deleteComment(memberDetails,id);
        return ResponseEntity.ok().build();
    }
//
    @PutMapping("/replies/{replies_id}")
    public ResponseEntity<String> updateReply(
    @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("replies_id") Long id,
            @RequestBody ReplyRequest ReplyRequest){

        replyService.updateComment(memberDetails,id,ReplyRequest);
        return ResponseEntity.ok().build();
    }
}
