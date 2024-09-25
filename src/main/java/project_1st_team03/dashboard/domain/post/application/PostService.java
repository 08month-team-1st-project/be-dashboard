package project_1st_team03.dashboard.domain.post.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.member.exception.MemberException;
import project_1st_team03.dashboard.domain.post.dao.PostRepository;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.domain.post.dto.PostDetailDto;
import project_1st_team03.dashboard.domain.post.dto.PostListResponse;
import project_1st_team03.dashboard.domain.post.dto.PostRequest;
import project_1st_team03.dashboard.domain.post.exception.PostException;
import project_1st_team03.dashboard.global.security.MemberDetails;

import java.util.Objects;

import static project_1st_team03.dashboard.global.exception.ErrorCode.*;


/* Question
UserDetails 를 만들 때 db에 접근해서 엔티티를 가져왔었는데 (UserDetails 은 dto 형태로 만든 상태)
service 안에서 또 db에 접근하여 엔티티를 또 꺼내올 것인가..?
db 에 왔다갔다하는 횟수가 많아서 성능에는 문제가 없을까?

그렇다고, userDetails 에 들어있는 값으로 엔티티를 만들어서 사용하면, 준영속 상태일텐데
준영속 엔티티는 db에 저장 시에 merge 형태로 저장하니까 자칫하면 db에 있는 값이 잘못 덮어씌워질 위험도 있고..
*/
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createPost(MemberDetails memberDetails, PostRequest request) {

        Member member = memberRepository.findByEmail(memberDetails.getUsername())
                .orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();

        postRepository.save(post);
    }

    @Transactional
    public void updatePost(MemberDetails memberDetails, long postId, PostRequest request) {

        Post findPost = postRepository
                .findById(postId).orElseThrow(() -> new PostException(NOT_FOUND_POST));

        // 본인이 작성하지 않은 게시글은 수정할 수 없음
        if (!Objects.equals(memberDetails.getId(), findPost.getMember().getId())) {
            throw new PostException(UNAUTHORIZED_EDIT_POST_ATTEMPT);
        }

        findPost.updateTitleAndContent(request.getTitle(), request.getContent());
    }

    public Page<PostListResponse> getPostPage(Pageable pageable) {
        return postRepository.findPostPage(pageable)
                .map(PostListResponse::new);
    }

    public Page<PostListResponse> searchPostPage(String email, Pageable pageable) {
        return postRepository.findPostPageByEmail(email, pageable)
                .map(PostListResponse::new);
    }

    public PostDetailDto getPostDetail(long postId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(NOT_FOUND_POST));

        return new PostDetailDto(findPost);
    }
}
