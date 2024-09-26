package project_1st_team03.dashboard.domain.comment.application;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import project_1st_team03.dashboard.domain.comment.dao.CommentRepository;
import project_1st_team03.dashboard.domain.comment.domain.Comment;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.domain.member.dao.MemberRepository;
import project_1st_team03.dashboard.domain.member.domain.Member;
import project_1st_team03.dashboard.domain.post.domain.Post;
import project_1st_team03.dashboard.global.security.MemberDetails;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
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
    List<Comment> comments;


    //test가 실행되기전 실행
    @BeforeEach
    void beforeEach() {
        content = "This is a comment";

        member = new Member("asd@asd.com", "12345678!a", null, null, null, null);

        comments = new ArrayList<>(Arrays.asList(
                new Comment(null,member,"안녕하세요",null),
                new Comment(null,member,"반갑습니다.",null)
        ));
    }

    @Test
    @DisplayName("댓글 생성")
    void createComments() {
        // Given: 테스트에 필요한 Mock 데이터 설정
        content = "새로운 댓글 내용";
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
    @DisplayName("데이터베이스에 없는 email로 댓글 생성을 시도")
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

    @Test
    @DisplayName("댓글 조회")
    void getAllComment() {
        //given
        when(commentRepository.findAll()).thenReturn(comments);

        //when
       List<CommentsResponse>commentsResponses = commentService.getAllComment();

       //then
        List<String> contents =commentsResponses.stream()
                .map(CommentsResponse::getContent) // CommentsResponse에서 content 필드 추출
                .toList(); // List로 수집
        contents.forEach(System.out::println);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
        //given
        MemberDetails mockMemberDetails = new MemberDetails(member);
        Long commentId = 1L;
        //레포지토리에서 찾을때 comments.get(0)값으로 반환해줌.
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comments.get(0)));
        //where
        commentService.deleteComment(mockMemberDetails, commentId);

        //then
        verify(commentRepository, times(1)).deleteById(commentId);

    }

    @Test
    @DisplayName("댓글 수정")
    void updateComment() {
        //given
        MemberDetails mockMemberDetails = new MemberDetails(member);
        Long postId = 1L;
        Long commentId =1L;
        CommentsRequest commentsRequest = new CommentsRequest("author", content, postId);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comments.get(0)));
        //whe
        commentService.updateComment(mockMemberDetails, commentId,commentsRequest);

        //then
        verify(commentRepository, times(1)).save(any(Comment.class));
        assert comments.get(0).getContent().equals("This is a comment");//바뀌었는지 확인
    }
}

