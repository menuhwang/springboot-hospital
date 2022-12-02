package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.config.SecurityConfig;
import com.likelion.hospital.domain.dto.reply.ReplyReqDTO;
import com.likelion.hospital.domain.dto.reply.ReplyResDTO;
import com.likelion.hospital.service.ReplyService;
import com.likelion.hospital.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReplyApiController.class)
@ImportAutoConfiguration(SecurityConfig.class)
class ReplyApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReplyService replyService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.author").value("author"))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content").value("content"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    void getRepliesByBoardId() throws Exception {
        List<ReplyResDTO> replyResDTOList = new ArrayList<>();
        for (long i = 1; i < 5L; i++) {
            replyResDTOList.add(ReplyResDTO.builder()
                    .id(i)
                    .author("author" + i)
                    .content("content" + i)
                    .build());
        }

        given(replyService.findByBoardId(any(Long.class))).willReturn(replyResDTOList);

        mockMvc.perform(get("/api/v1/replies").param("board", "1"))
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].author").exists())
                .andExpect(jsonPath("$[*].content").exists())
                .andDo(print());
    }
}