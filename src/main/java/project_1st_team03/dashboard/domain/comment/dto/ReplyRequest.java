package project_1st_team03.dashboard.domain.comment.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import project_1st_team03.dashboard.domain.comment.domain.Comment;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReplyRequest {

    private Long commentId;
    //private String author;

    @Length(min = 3, max = 50, message = "댓글은 3 ~ 50 자 내로 적어주세요.")
    private String content;
}
