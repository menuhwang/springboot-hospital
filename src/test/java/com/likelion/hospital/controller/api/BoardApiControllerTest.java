package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.board.BoardDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    BoardDTO boardDTO = new BoardDTO(1L, "author", "title", "content");

    @Test
    void create() throws Exception {
        given(boardService.create(any(BoardDTO.class))).willReturn(boardDTO);
        mockMvc.perform(post("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"author\" :  \"author\"," +
                        "\"title\" :  \"title\"," +
                        "\"content\" :  \"content\"" +
                        "}"))
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
    }

    @Test
    void getOneById() throws Exception {
        given(boardService.getOneById(1L)).willReturn(boardDTO);
        mockMvc.perform(get("/api/v1/boards/1"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.content").value("content"));
    }
}