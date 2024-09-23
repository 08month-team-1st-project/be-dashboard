package project_1st_team03.dashboard.domain.like.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.like.dao.LikeRepository;
import project_1st_team03.dashboard.domain.like.domain.Like;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    // 1. 좋아요 추가/취소 C/D
    @Transactional
    public void toggleLike(Post post, Member member) {
        Optional<Like> existingLike = // 좋아요 존재 확인
                likeRepository.findByPostAndMember(post, member);

        // post, member 비었는지 확인, 비어있지 않다면 취소
        existingLike.ifPresent(likeRepository::delete);

        // 비어있으면 저장
        if (!existingLike.isPresent()) {
            Like like = new Like();
            like.setPost(post);
            like.setMember(member);
            likeRepository.save(like);
        }
    }
}