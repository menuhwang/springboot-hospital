package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.domain.dto.board.BoardReqDTO;
import com.likelion.hospital.domain.dto.board.BoardResDTO;
import com.likelion.hospital.domain.dto.board.BoardResWithReplyDTO;
import com.likelion.hospital.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardApiController.class)
class BoardApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private BoardService boardService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(status().isOk())
                .alwaysExpect(jsonPath("$.id").exists())
                .alwaysExpect(jsonPath("$.author").exists())
                .alwaysExpect(jsonPath("$.title").exists())
                .alwaysExpect(jsonPath("$.content").exists())
                .alwaysDo(print())
                .build();
    }

    BoardResDTO boardResDTO = new BoardResDTO(1L, "author", "title", "content");
    BoardResWithReplyDTO boardResWithReplyDTO = BoardResWithReplyDTO.builder()
            .id(1L)
            .author("author")
            .title("title")
            .content("content")
            .build();

    @Test
    void create() throws Exception {
        BoardReqDTO editDTO = BoardReqDTO.builder()
                .author("author")
                .title("title")
                .content("content")
                .build();
        given(boardService.create(any(BoardReqDTO.class))).willReturn(boardResDTO);
        mockMvc.perform(post("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8") //objectMapper 사용시 RequestBody가 <no character encoding set> 이라고 뜨는 문제가 발생. 따라서 인코딩 설정.
                .content(objectMapper.writeValueAsString(editDTO)))
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
        verify(boardService).create(any(BoardReqDTO.class));
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
                .author("edit-author")
                .title("edit-title")
                .content("edit-content")
                .build();
        BoardResDTO mockDTO = BoardResDTO.builder()
                .id(1L)
                .author("edit-author")
                .title("edit-title")
                .content("edit-content")
                .build();

        given(boardService.editById(eq(1L), any(BoardReqDTO.class))).willReturn(mockDTO);
        // 인자에 any와 raw 값을 같이 넣을 수 없다.()

        mockMvc.perform(patch("/api/v1/boards/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(editDTO)));
        verify(boardService).editById(eq(1L), any(BoardReqDTO.class));
    }
}