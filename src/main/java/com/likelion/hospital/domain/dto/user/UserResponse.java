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
    private String userName;
    private String emailAddress;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .emailAddress(user.getEmailAddress())
                .build();
    }
}
