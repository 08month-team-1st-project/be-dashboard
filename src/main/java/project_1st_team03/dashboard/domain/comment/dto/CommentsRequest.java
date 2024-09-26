package project_1st_team03.dashboard.domain.comment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentsRequest {


//    @NotBlank(message = "게시글 아이디가 null 일 수는 없습니다.")
    private Long postId;

    @Length(min = 3, max = 50, message = "댓글은 3 ~ 50 자 내로 적어주세요.")
    private String content;

}
