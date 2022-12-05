package com.likelion.hospital.domain.dto.user;

import com.likelion.hospital.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpDTO {
    private String username;
    private String password;
    private String email;

    public User toEntity(String encodePassword) {
        return User.builder()
                .username(username)
                .password(encodePassword)
                .email(email)
                .build();
    }
}
