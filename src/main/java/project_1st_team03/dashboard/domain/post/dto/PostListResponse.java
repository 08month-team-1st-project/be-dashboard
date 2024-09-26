package project_1st_team03.dashboard.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import project_1st_team03.dashboard.domain.post.domain.Post;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
public class PostListResponse {

    private Long id;

    private String author;

    private String title;
    
    // TODO 그대로 반환할지 고민 (일단 전체 반환으로 해놓음)
    private String content;

    private long likeCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;



    public PostListResponse(Post post) {
        this.id = post.getId();
        this.author = post.getMember().getEmail(); // N+1
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
