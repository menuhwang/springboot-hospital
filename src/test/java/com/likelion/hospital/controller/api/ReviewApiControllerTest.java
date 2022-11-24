package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewApiController.class)
class ReviewApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReviewService reviewService;

    @Test
    void findById() throws Exception {
        ReviewResDTO reviewResDTO = ReviewResDTO.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        given(reviewService.findById(anyLong())).willReturn(reviewResDTO);

        mockMvc.perform(get("/api/v1/reviews/" + 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
        verify(reviewService).findById(anyLong());
    }
}