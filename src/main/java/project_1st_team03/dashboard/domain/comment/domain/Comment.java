package project_1st_team03.dashboard.domain.comment.domain;

import jakarta.persistence.*;
import lombok.*;
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


//    // Comment 리스트를 CommentsResponse 리스트로 변환하는 메서드
//    public List<CommentsResponse> toCommentsResponseList(List<Comment> comments) {
//        return comments.stream().map(comment ->
//                CommentsResponse.builder()
//                        .content(comment.getContent())
//                        .memberId(comment.getMember()) // memberId는 Member 객체에서 가져오는 방식으로 수정
//                        .postId(comment.getPost())     // postId도 Post 객체에서 가져오는 방식으로 수정
//                        .createdAt(comment.getCreatedAt())
//                        .modifiedAt(comment.getModifiedAt())
//                        .build()
//        ).collect(Collectors.toList());
//    }
}
