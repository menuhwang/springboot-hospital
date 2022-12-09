package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.config.SecurityConfig;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.service.UserService;
import com.likelion.hospital.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TempUserController.class)
//@WebMvcTest(SignApiController.class)
@ImportAutoConfiguration(SecurityConfig.class) // for security test
class TempUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtTokenUtil jwtTokenUtil; // for security test
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void createUser() throws Exception {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
        UserResponse userResponse = UserResponse.builder()
                .username(USERNAME)
                .email(EMAIL)
                .build();

        given(userService.join(any(SignUpDTO.class))).willReturn(userResponse);



//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signup")
        mockMvc.perform(MockMvcRequestBuilders.post("/tempUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(USERNAME))
                .andExpect(jsonPath("$.email").value(EMAIL))
//                .andExpect(jsonPath("$.userName").value(USERNAME))
//                .andExpect(jsonPath("$.emailAddress").value(EMAIL))
                .andDo(print());

        verify(userService).join(any(SignUpDTO.class));
    }
}