package project_1st_team03.dashboard.domain.post.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.post.application.PostService;
import project_1st_team03.dashboard.domain.post.dto.PostListResponse;
import project_1st_team03.dashboard.domain.post.dto.PostRequest;

/**
 * TODO
 * 회원인증 로직 연결
 */
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@Valid @RequestBody PostRequest request) {
        postService.createPost(request);
        return ResponseEntity.ok("게시물이 성공적으로 작성되었습니다.");
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable long postId, @Valid @RequestBody PostRequest request) {
        postService.updatePost(postId, request);
        return ResponseEntity.ok("게시물이 성공적으로 수정되었습니다.");
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostListResponse>> getPosts(Pageable pageable) {
        return ResponseEntity.ok(postService.getPostPage(pageable));
    }

    @GetMapping("/posts/search")
    public ResponseEntity<Page<PostListResponse>> searchPosts(
                                @RequestParam("autor_email") String autorEmail,
                                Pageable pageable) {
        return ResponseEntity.ok(postService.searchPostPage(autorEmail, pageable));
    }
}
