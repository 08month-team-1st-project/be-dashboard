package project_1st_team03.dashboard.domain.comment.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;


@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("comment 등록 api")
    void createComment() throws Exception {

        // given
        ObjectMapper objectMapper = new ObjectMapper();
        CommentsRequest comments = new CommentsRequest
                ("이것은 댓글입니다.", "사용자1", 1L);
        String jsonContent = objectMapper.writeValueAsString(comments);
         /*
    when
    then
     */
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("comment 조회 api")
    @Test
    void getAllComments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @DisplayName("comment 삭제 api")
    @Test
    void deleteCommentByPathId() throws Exception {
        //given
        Integer id= 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/comments/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @DisplayName("comment 수정 api")
    @Test
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
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}