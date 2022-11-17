package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ReplyApiController.class)
class ReplyApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReplyService replyService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        ReplyReqDTO replyReqDTO = ReplyReqDTO.builder()
                .boardId(1L)
                .author("author")
                .content("content")
                .build();

        ReplyResDTO replyResDTO = ReplyResDTO.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        given(replyService.create(any(ReplyReqDTO.class))).willReturn(replyResDTO);

        mockMvc.perform(post("/api/v1/replies")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(replyReqDTO)))
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("content"));
    }
}