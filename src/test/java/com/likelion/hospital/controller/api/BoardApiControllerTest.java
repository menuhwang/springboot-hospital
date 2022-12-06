package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.config.SecurityConfig;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.service.BoardService;
import com.likelion.hospital.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardApiController.class)
@ImportAutoConfiguration(SecurityConfig.class)
class BoardApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BoardService boardService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String AUTHORIZATION = "Authorization";
    private final String TOKEN = "testMockToken";
    private final User USER = new User(1L, "testUser", "password", "testUser@email.com");

    BoardResDTO boardResDTO = new BoardResDTO(1L, "author", "title", "content");
    BoardResWithReplyDTO boardResWithReplyDTO = BoardResWithReplyDTO.builder()
            .id(1L)
            .author("author")
            .title("title")
            .content("content")
            .build();

    @Test
    @WithMockUser
    void create() throws Exception {
        BoardReqDTO editDTO = BoardReqDTO.builder()
                .title("title")
                .content("content")
                .build();

        given(boardService.create(any(BoardReqDTO.class), any(User.class))).willReturn(boardResDTO);

        mockMvc.perform(post("/api/v1/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8") //objectMapper 사용시 RequestBody가 <no character encoding set> 이라고 뜨는 문제가 발생. 따라서 인코딩 설정.
                        .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"))
                .andDo(print());

        verify(boardService).create(any(BoardReqDTO.class), any(User.class));
    }

    @Test
    void create_No_User_Login() throws Exception {
        BoardReqDTO editDTO = BoardReqDTO.builder()
                .title("title")
                .content("content")
                .build();

        given(boardService.create(any(BoardReqDTO.class), any(User.class))).willReturn(boardResDTO);

        mockMvc.perform(post("/api/v1/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8") //objectMapper 사용시 RequestBody가 <no character encoding set> 이라고 뜨는 문제가 발생. 따라서 인코딩 설정.
                        .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(status().is(403))
                .andDo(print());
    }

    @Test
    void getOneById() throws Exception {
        given(boardService.getById(1L)).willReturn(boardResWithReplyDTO);
        mockMvc.perform(get("/api/v1/boards/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
        verify(boardService).getById(1L);
    }

    @Test
    void editOneById() throws Exception {
        BoardReqDTO editDTO = BoardReqDTO.builder()
                .title("edit-title")
                .content("edit-content")
                .build();
        BoardResDTO mockDTO = BoardResDTO.builder()
                .id(1L)
                .author("edit-author")
                .title("edit-title")
                .content("edit-content")
                .build();

        given(boardService.editById(eq(1L), any(BoardReqDTO.class), any(User.class))).willReturn(mockDTO);
        // 인자에 any와 raw 값을 같이 넣을 수 없다.()

        mockMvc.perform(patch("/api/v1/boards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(editDTO)));
        verify(boardService).editById(eq(1L), any(BoardReqDTO.class), any(User.class));
    }
}