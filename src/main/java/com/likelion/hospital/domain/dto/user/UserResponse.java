package com.likelion.hospital.domain.dto.user;

import com.likelion.hospital.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
