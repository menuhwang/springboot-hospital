package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.DuplicateUsernameException;
import com.likelion.hospital.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceImplTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserServiceImpl(userRepository);

    @Test
    void join_정상() {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();
        User saved = User.builder()
                .id(1L)
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();
        given(userRepository.findByUserName("test")).willReturn(Optional.empty());
        given(userRepository.save(any(User.class))).willReturn(saved);

        UserResponse result = userService.join(signUpDTO);

        assertNotNull(result.getId());
        assertEquals(USERNAME, result.getUserName());
        assertEquals(EMAIL, result.getEmailAddress());
    }

    @Test
    void join_아이디_중복() {
        String USERNAME = "test";
        String EMAIL = "test@gmail.com";
        SignUpDTO signUpDTO = SignUpDTO.builder()
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();
        User user = User.builder()
                .id(1L)
                .userName(USERNAME)
                .emailAddress(EMAIL)
                .build();

        given(userRepository.findByUserName("test")).willReturn(Optional.of(user));

        assertThrows(DuplicateUsernameException.class, () -> userService.join(signUpDTO));
    }
}