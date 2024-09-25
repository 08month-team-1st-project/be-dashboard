package project_1st_team03.dashboard.domain.comment.application;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.global.security.MemberDetails;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CommentService commentService; //commentService = new CommentService(commentRepository);

    String content;
    Member member;
    Post post;

    @Test
    void createComments() {
        // Given: 테스트에 필요한 Mock 데이터 설정
        String content = "새로운 댓글 내용";
        Long postId = 1L;
        String email = "asd@asd.com";
        CommentsRequest commentsRequest = new CommentsRequest("author", content, postId);

        Member mockMember = new Member(email, "12345678!a", null, null, null, null);

        MemberDetails mockMemberDetails = new MemberDetails(mockMember);

        // Member를 찾을 때, 해당 mockMember를 반환하도록 설정
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(mockMember));

        // When: 서비스 메서드 호출
        commentService.createComments(mockMemberDetails, commentsRequest);

        // Then: 검증
        // CommentRepository의 save 메서드가 적절한 인자로 한 번 호출되었는지 확인
        verify(commentRepository, times(1)).save(any(Comment.class));
    }
    @Test
    void createComments_shouldThrowException_whenMemberNotFound() {
        // Given: memberRepository가 빈 Optional을 반환하도록 설정
        String email = "notfound@asd.com";
        Long postId = 1L;
        CommentsRequest commentsRequest = new CommentsRequest("author", "내용", postId);
        MemberDetails memberDetails = new MemberDetails(new Member(email, "12345678!a", null, null, null, null));

        // memberRepository.findByEmail() 호출 시 빈 Optional 반환
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then: 예외가 발생하는지 확인
        assertThrows(EntityNotFoundException.class, () ->
                commentService.createComments(memberDetails, commentsRequest));
    }
}

