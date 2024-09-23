package project_1st_team03.dashboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.member.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
public final CommentRepository commentRepository;

    public void createComments(CommentsRequest commentsRequest) {
        Comment comment = Comment.builder()
                .content(commentsRequest.getContent())
                .member(null)
                .post(null)
                .build();
        commentRepository.save(comment);
    }

    public List<CommentsResponse> getAllComment() {
        List<Comment> comments = commentRepository.findAll();

        List<CommentsResponse> commentsResponses = comments.stream().map((comment)->
            CommentsResponse.builder()
                    .content(comment.getContent())
                    .memberId(1L)
                    .postId(1)
                    .createdAt(comment.getCreatedAt())
                    .modifiedAt(comment.getModifiedAt())
                    .build()
        ).collect(Collectors.toList());
        return commentsResponses;

    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Long id, CommentsRequest commentRequest) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Comment not found"));
        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);

    }

}
