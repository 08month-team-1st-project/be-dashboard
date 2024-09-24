package project_1st_team03.dashboard.domain.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.common.BaseEntity;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@Entity
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 50)
    private String content;


//    public Comment(CommentsRequest commentsRequest) {
//        this.content = commentsRequest.getContent();
//        this.post = commentsRequest.getPostId();
//        this.member = commentsRequest.getAuthor();
//    }
}
