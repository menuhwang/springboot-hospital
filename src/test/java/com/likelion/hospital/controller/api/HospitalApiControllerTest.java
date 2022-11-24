package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.service.HospitalService;
import com.likelion.hospital.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalApiController.class)
class HospitalApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HospitalService hospitalService;
    @MockBean
    private ReviewService reviewService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Hospital hospital = Hospital.builder()
            .id(2321)
            .hospitalName("노소아청소년과의원")
            .fullAddress("서울특별시 서초구 반포동 455번지 4호 동화프라자빌딩 202호")
            .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
            .healthcareProviderCount(1)
            .patientRoomCount(0)
            .totalAreaSize(0F)
            .businessStatus(1)
            .businessStatusCode(13)
            .build();

    @Test
    void findById() throws Exception {
        Integer ID = 2321;
        given(hospitalService.findById(ID)).willReturn(HospitalResWithReviewDTO.of(hospital));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hospitals/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.hospitalName").exists())
                .andExpect(jsonPath("$.fullAddress").exists())
                .andExpect(jsonPath("$.roadNameAddress").exists())
                .andExpect(jsonPath("$.healthcareProviderCount").exists())
                .andExpect(jsonPath("$.patientRoomCount").exists())
                .andExpect(jsonPath("$.totalAreaSize").exists())
                .andExpect(jsonPath("$.shutDown").exists())
                .andDo(print());
    }

    @Test
    void createReview() throws Exception {
        Integer boardId = 1;
        ReviewReqDTO reviewReqDTO = ReviewReqDTO.builder()
                .author("author")
                .content("content")
                .build();

        ReviewResDTO reviewResDTO = ReviewResDTO.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        given(reviewService.create(anyInt(), any(ReviewReqDTO.class))).willReturn(reviewResDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hospitals/" + boardId + "/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewReqDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
    }
}