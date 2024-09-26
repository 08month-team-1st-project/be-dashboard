package project_1st_team03.dashboard.domain.comment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentsResponse {
    private Long id;
    private String content;
    private Long postId;
    private Long memberId;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentsResponse(Comment comment,String email) {
        this.id = comment.getId();
        content = comment.getContent();
        postId = comment.getPost().getId();
        memberId = comment.getMember().getId();
        author = email;
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
    }

}
