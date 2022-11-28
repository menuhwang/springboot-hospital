package com.likelion.hospital.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.exception.conflict.DuplicateUsernameException;
import com.likelion.hospital.exception.forbidden.SignInForbiddenException;
import com.likelion.hospital.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignApiController.class)
class SignApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void join_정상() throws Exception {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .password(PASSWORD)
                .build();
        UserResponse userResponse = UserResponse.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();

        given(userService.join(any(SignUpDTO.class))).willReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(USERNAME))
                .andExpect(jsonPath("$.emailAddress").value(EMAIL))
                .andDo(print());

        verify(userService).join(any(SignUpDTO.class));
    }

    @Test
    void join_아이디_중복() throws Exception {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .password(PASSWORD)
                .build();

        given(userService.join(any(SignUpDTO.class))).willThrow(new DuplicateUsernameException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("이미 존재하는 아이디입니다."))
                .andDo(print());
        verify(userService).join(any(SignUpDTO.class));
    }

    @Test
    void login_정상() throws Exception {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignInDTO signInDTO = SignInDTO.builder()
                .userName(USERNAME)
                .password(PASSWORD)
                .build();

        UserResponse userResponse = UserResponse.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();

        given(userService.login(any(SignInDTO.class))).willReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(USERNAME))
                .andExpect(jsonPath("$.emailAddress").value(EMAIL))
                .andDo(print());

        verify(userService).login(any(SignInDTO.class));
    }

    @Test
    void login_비밀번호_불일치() throws Exception {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignInDTO signInDTO = SignInDTO.builder()
                .userName(USERNAME)
                .password(PASSWORD)
                .build();

        given(userService.login(any(SignInDTO.class))).willThrow(new SignInForbiddenException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInDTO)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("아이디 또는 비밀번호를 확인해주세요."))
                .andDo(print());

        verify(userService).login(any(SignInDTO.class));
    }
}