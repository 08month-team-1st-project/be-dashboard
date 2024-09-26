package project_1st_team03.dashboard.domain.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.global.security.JwtService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WithMockUser(username = "testUser")
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    JwtService jwtService;

    @Test
    @DisplayName("comment 생성 api")
    public void testCreateComment() throws Exception {

        // 댓글 생성 요청 데이터
        CommentsRequest request = new CommentsRequest("Author","content",1L);


        // JSON으로 요청을 전송
        mockMvc.perform(post("/api/comments")  // 요청 경로 확인
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @DisplayName("comment 조회 api")
    @Test
    void getAllComments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    @DisplayName("comment 삭제 api")
    @Test
    @WithMockUser(roles = "USER")
    void deleteCommentByPathId() throws Exception {
        //given
        Integer id= 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/comments/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    @DisplayName("comment 수정 api")
    @Test
    @WithMockUser(roles = "USER")
    void updateComment() throws Exception {
        //given
        Integer id= 1;
        ObjectMapper objectMapper = new ObjectMapper();
        CommentsRequest comments = new CommentsRequest
                ("이것은 댓글입니다.", "사용자1", 1L);
        String jsonContent = objectMapper.writeValueAsString(comments);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/comments/" +id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}