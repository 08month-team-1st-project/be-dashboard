package project_1st_team03.dashboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.dao.ReplyRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.domain.Reply;
import project_1st_team03.dashboard.domain.comment.dto.ReplyRequest;
import project_1st_team03.dashboard.domain.comment.dto.ReplyResponse;
import project_1st_team03.dashboard.domain.comment.exception.CommentException;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.global.exception.ErrorCode;
import project_1st_team03.dashboard.global.security.MemberDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createComments(
            MemberDetails memberDetails,
            ReplyRequest request) {

        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(()->new CommentException(ErrorCode.NOT_FOUND_COMMENT));
        Member member = memberRepository.findByEmail(memberDetails.getEmail())
                .orElseThrow(()->new CommentException(ErrorCode.NOT_FOUND_COMMENT));

        Reply reply =Reply.builder()
                .comment(comment)
                .member(member)
                .content(request.getContent())
                .build();
        ;
        replyRepository.save(reply);
    }

    public List<ReplyResponse> getAllComment() {
        List<Reply> replies = replyRepository.findAll();
       List<ReplyResponse> responses = replies.stream()
               .map(ReplyResponse::new).toList();

        return  responses;
    }

    @Transactional
    public void deleteComment(MemberDetails memberDetails, Long id) {

        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new CommentException(ErrorCode.NOT_FOUND_COMMENT));
        if (!reply.getMember().getEmail().equals(memberDetails.getUsername())) {
            throw new CommentException(ErrorCode.UNAUTHORIZED_EDIT_COMMENT_ATTEMPT);
        }

        replyRepository.deleteById(id);
    }

    @Transactional
    public void updateComment(MemberDetails memberDetails, Long id, ReplyRequest replyRequest) {

        Reply reply = replyRepository.findById(id)
                .orElseThrow(()->new CommentException(ErrorCode.NOT_FOUND_COMMENT));
        if (!reply.getMember().getEmail().equals(memberDetails.getUsername())) {
            throw new CommentException(ErrorCode.UNAUTHORIZED_EDIT_COMMENT_ATTEMPT);
        }

        reply.setContent(replyRequest.getContent());
        replyRepository.save(reply);
    }
}
