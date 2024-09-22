package project_1st_team03.dashboard.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.domain.post.dto.CreatePostRequest;

/**
 * TODO 회원인증 로직 연결 , 관련 예외 수정
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createPost(CreatePostRequest request) {

        // TODO 회원관련 로직 수정
        Member member = memberRepository
                .findById(1L)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));
        
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();
        postRepository.save(post);
    }
}
