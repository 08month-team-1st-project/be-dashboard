package project_1st_team03.dashboard.domain.comment.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService; //commentService = new CommentService(commentRepository);

    String content;
    Member member;
    Post post;

    @Test
    void createComments() {
        String content = "새로운 댓글 내용";
        CommentsRequest commentsRequest = new CommentsRequest("author",content,1L);


//        Post post = new Post(); // Post 객체를 생성
//        post.(commentsRequest.getPostId()); // Post의 ID 설정
//        mockComment.setPost(post);

        Comment mockComment = new Comment();
       // mockComment.setMember(null); //
        mockComment.setContent(commentsRequest.getContent());
      //  mockComment.setPost(null);

        when(commentRepository.save(any(Comment.class))).thenReturn(mockComment);

        commentService.createComments(commentsRequest);

        verify(commentRepository).save(any(Comment.class));  // 특정 Comment 객체가 저장되었는지 검증



//        assertThat(result.getText()).isEqualTo("새로운 게시물 내용");


    }

}