package project_1st_team03.dashboard.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.common.BaseEntity;
import project_1st_team03.dashboard.domain.like.domain.Like;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.global.security.MemberRole;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private MemberRole role;

    public static Member createMember(String email, String password) {
        Member member = new Member();
        member.email = email;
        member.password = password;

        return member;
    }
}
