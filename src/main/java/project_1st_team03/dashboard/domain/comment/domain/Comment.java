package project_1st_team03.dashboard.domain.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.common.BaseEntity;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter // Setter 지양
@Entity
public class Comment extends BaseEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 50)
    private String content;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replies = new ArrayList<>();

    public Comment(Post post, Member member, String content) {
        this.post = post;
        this.member = member;
        this.content = content;
    }


    // Comment: 엔티티에서는 프래젠테이션 계층 의존하지 않도록 해주세요 , 엔티티는 최대한 순수하게 유지
//    public Comment(CommentsRequest commentsRequest) {
//        this.content = commentsRequest.getContent();
//        this.post = commentsRequest.getPostId();
//        this.member = commentsRequest.getAuthor();
//    }
}
