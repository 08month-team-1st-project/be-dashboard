package project_1st_team03.dashboard.domain.post.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project_1st_team03.dashboard.domain.post.application.PostService;
import project_1st_team03.dashboard.domain.post.dto.CreatePostRequest;

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
    public ResponseEntity<String> createPost(@Valid @RequestBody CreatePostRequest request) {
        postService.createPost(request);
        return ResponseEntity.ok("게시물이 성공적으로 작성되었습니다.");
    }


}
