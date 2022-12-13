package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.config.SecurityConfig;
import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.service.HospitalService;
import com.likelion.hospital.service.ReviewService;
import com.likelion.hospital.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalApiController.class)
@ImportAutoConfiguration(SecurityConfig.class)
class HospitalApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HospitalService hospitalService;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;
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
    @WithMockUser
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
    @WithMockUser
    void createReview() throws Exception {
        Integer hospitalId = 1;
        ReviewReqDTO reviewReqDTO = ReviewReqDTO.builder()
                .content("content")
                .build();

        ReviewResDTO reviewResDTO = ReviewResDTO.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        given(reviewService.create(anyInt(), any(ReviewReqDTO.class), any(Principal.class))).willReturn(reviewResDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hospitals/" + hospitalId + "/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewReqDTO))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());
    }

    @Test
    @WithMockUser
    void findReviewsByHospital() throws Exception {
        Integer hospitalId = 1;
        List<ReviewResDTO> reviewResDTOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            reviewResDTOList.add(ReviewResDTO.builder()
                    .id((long) i)
                    .author("author" + i)
                    .content("content" + i)
                    .build());
        }

        given(reviewService.findByHospital(hospitalId)).willReturn(reviewResDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hospitals/" + hospitalId + "/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].author").exists())
                .andExpect(jsonPath("$[*].content").exists())
                .andDo(print());
    }
}