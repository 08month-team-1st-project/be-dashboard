package project_1st_team03.dashboard.domain.post.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.common.BaseEntity;
import project_1st_team03.dashboard.domain.like.domain.Like;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.type.PostStatus;

import java.util.ArrayList;
import java.util.List;

import static project_1st_team03.dashboard.domain.post.type.PostStatus.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseEntity {

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Member member;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    private long likeCount;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Like> likes = new ArrayList<>();

    // 게시글 삭제 시 바로 db에서 지우는 것이 아닌 체크방식으로 하였음
    // 현재 프로젝트에서 구현하지는 않지만 확장성을 고려해서 enum으로 관리 ex) 정상, 삭제, 신고상태
    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    public Post(Member member, String title, String content, PostStatus status) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public void updateTitleAndContent(String title, String content) {

        if(StringUtils.hasText(title)){
            this.title = title;
        }
        if(StringUtils.hasText(content)){
            this.content = content;
        }
    }

    public void delete() {
        status = DELETE;
    }
}
