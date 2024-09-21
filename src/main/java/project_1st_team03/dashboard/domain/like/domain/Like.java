package project_1st_team03.dashboard.domain.like.domain;

import jakarta.persistence.*;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;

@Table(name = "likes") // like 키워드가 예약어라서 따로 네이밍해주었음
@Entity
public class Like {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
