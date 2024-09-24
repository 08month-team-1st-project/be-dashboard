package project_1st_team03.dashboard.domain.comment.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.dao.ReplyRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.domain.Reply;
import project_1st_team03.dashboard.domain.comment.dto.ReplyRequest;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    public void createComments(ReplyRequest request) {

        // 정수 1을 이용해 Comment 엔티티 조회
        Comment comment = commentRepository.findById(request.getCommentId()).orElse(null);

        Reply reply =Reply.builder()
                .comment(comment)
                .member(null)
                .content(request.getContent())
                .build();
        ;
        replyRepository.save(reply);
    }
}
