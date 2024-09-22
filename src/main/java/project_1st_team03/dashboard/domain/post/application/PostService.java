package project_1st_team03.dashboard.domain.post.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.domain.post.dto.PostRequest;
import project_1st_team03.dashboard.domain.post.exception.PostException;

import java.util.Objects;

import static project_1st_team03.dashboard.global.exception.ErrorCode.*;

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
    public void createPost(PostRequest request) {

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

    @Transactional
    public void updatePost(long postId, PostRequest request) {

        // TODO 회원관련 로직 수정
        Member member = memberRepository
                .findById(1L)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원"));

        Post findPost = postRepository
                .findById(postId).orElseThrow(() -> new PostException(NOT_FOUND_POST));

        // 본인이 작성하지 않은 게시글은 수정할 수 없음
        if(!Objects.equals(member.getId(), findPost.getMember().getId())) {
            throw new PostException(UNAUTHORIZED_EDIT_POST_ATTEMPT);
        }

        findPost.updateTitleAndContent(request.getTitle(), request.getContent());
    }
}
