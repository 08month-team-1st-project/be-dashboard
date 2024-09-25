package project_1st_team03.dashboard.domain.comment.domain;

import jakarta.persistence.*;
import lombok.*;
import project_1st_team03.dashboard.domain.common.BaseEntity;
import project_1st_team03.dashboard.domain.member.domain.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment; // Reference to the parent comment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 50)
    private String content;

}
