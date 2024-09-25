package project_1st_team03.dashboard.domain.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter //@Setter
@NoArgsConstructor
public class PostRequest {

    @Length(min = 5 , max = 30 ,message = "제목은 5 ~ 30자 내외로 입력해주세요")
    private String title;

    @Length(min = 5 , max = 500, message = "내용은 5 ~ 500자 내외로 입력해주세요")
    private String content;
}
