package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.service.HospitalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalApiController.class)
class HospitalApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private HospitalService hospitalService;

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

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysExpect(status().isOk())
                .alwaysExpect(jsonPath("$.id").exists())
                .alwaysExpect(jsonPath("$.hospitalName").exists())
                .alwaysExpect(jsonPath("$.fullAddress").exists())
                .alwaysExpect(jsonPath("$.roadNameAddress").exists())
                .alwaysExpect(jsonPath("$.healthcareProviderCount").exists())
                .alwaysExpect(jsonPath("$.patientRoomCount").exists())
                .alwaysExpect(jsonPath("$.totalAreaSize").exists())
                .alwaysExpect(jsonPath("$.shutDown").exists())
                .alwaysDo(print())
                .build();
    }

    @Test
    void findById() throws Exception {
        Integer ID = 2321;
        given(hospitalService.findById(ID)).willReturn(HospitalResWithReviewDTO.of(hospital));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hospitals/" + ID));
    }
}