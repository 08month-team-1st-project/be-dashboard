package project_1st_team03.dashboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.member.domain.Member;

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
}
