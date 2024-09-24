package project_1st_team03.dashboard.domain.comment.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project_1st_team03.dashboard.domain.comment.domain.Comment;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyRequest {
    private Long commentId;
    private String author;
    private String content;
}
