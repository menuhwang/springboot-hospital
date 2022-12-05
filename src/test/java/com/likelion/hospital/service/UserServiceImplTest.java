package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.conflict.DuplicateUsernameException;
import com.likelion.hospital.exception.forbidden.SignInForbiddenException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.UserRepository;
import com.likelion.hospital.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceImplTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final JwtTokenUtil jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
    private final UserService userService = new UserServiceImpl(userRepository, passwordEncoder, jwtTokenUtil);

    @Test
    void join_정상() {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        String PASSWORD = "password";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
        User saved = User.builder()
                .id(1L)
                .username(USERNAME)
                .email(EMAIL)
                .build();
        given(userRepository.findByUsername("test")).willReturn(Optional.empty());
        given(passwordEncoder.encode(PASSWORD)).willReturn(PASSWORD);
        given(userRepository.save(any(User.class))).willReturn(saved);

        UserResponse result = userService.join(signUpDTO);

        assertNotNull(result.getId());
        assertEquals(USERNAME, result.getUsername());
        assertEquals(EMAIL, result.getEmail());
    }

    @Test
    void join_아이디_중복() {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .username(USERNAME)
                .email(EMAIL)
                .build();
        User user = User.builder()
                .id(1L)
                .username(USERNAME)
                .email(EMAIL)
                .build();

        given(userRepository.findByUsername("test")).willReturn(Optional.of(user));

        assertThrows(DuplicateUsernameException.class, () -> userService.join(signUpDTO));
    }

    @Test
    void login_비밀번호_불일치() {
        String USERNAME = "test";
        String WRONG_PASSWORD = "qwer123";
        String PASSWORD = "password";
        String EMAIL = "test@gmail.com";
        SignInDTO signInDTO = SignInDTO.builder()
                .username(USERNAME)
                .password(WRONG_PASSWORD)
                .build();

        User user = User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .email(EMAIL)
                .build();

        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.of(user));

        assertThrows(SignInForbiddenException.class, () -> userService.login(signInDTO));
    }

    @Test
    void login_유저_정보_없음() {
        String USERNAME = "test";
        String PASSWORD = "password";
        String EMAIL = "test@gmail.com";
        SignInDTO signInDTO = SignInDTO.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        given(userRepository.findByUsername(USERNAME)).willReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.login(signInDTO));
    }
}