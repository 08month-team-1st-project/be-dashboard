package project_1st_team03.dashboard.domain.comment.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.comment.exception.CommentException;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.global.exception.ErrorCode;
import project_1st_team03.dashboard.global.security.MemberDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
public final CommentRepository commentRepository;
public final MemberRepository memberRepository;
public final PostRepository postRepository;

    @Transactional
    public void createComments(MemberDetails memberDetails,
            CommentsRequest commentsRequest) {

        Post post = postRepository.findById(commentsRequest.getPostId())
                .orElseThrow(() -> new CommentException(ErrorCode.NOT_FOUND_COMMENT));

        Member member = memberRepository.findByEmail( memberDetails.getUsername())
                .orElseThrow(() -> new CommentException(ErrorCode.NOT_FOUND_COMMENT));

        Comment comment = Comment.builder()
                .content(commentsRequest.getContent())
                .member(member)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentsResponse> getAllComment() {
        List<CommentsResponse> commentsResponses = commentRepository.findAllWithMemberEmail()
                .orElse(new ArrayList<CommentsResponse>());
        return commentsResponses ;
    }

    @Transactional
    public void deleteComment(MemberDetails memberDetails,Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException(ErrorCode.NOT_FOUND_COMMENT));

        if (!comment.getMember().getEmail().equals(memberDetails.getUsername())) {
            throw new CommentException(ErrorCode.UNAUTHORIZED_EDIT_COMMENT_ATTEMPT);
        }
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public void updateComment(MemberDetails memberDetails,
                              Long id, CommentsRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new CommentException(ErrorCode.NOT_FOUND_COMMENT));

        if (!comment.getMember().getEmail().equals(memberDetails.getUsername())) {
            throw new  CommentException(ErrorCode.UNAUTHORIZED_EDIT_COMMENT_ATTEMPT);
        }
        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);

    }

}
