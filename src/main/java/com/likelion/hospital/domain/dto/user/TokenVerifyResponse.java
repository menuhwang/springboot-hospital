package com.likelion.hospital.domain.dto.user;

import com.likelion.hospital.domain.entity.User;
import lombok.Getter;

@Getter
public class TokenVerifyResponse {
    private Long id;
    private String username;

    private TokenVerifyResponse() {
    }

    public TokenVerifyResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static TokenVerifyResponse of(User user) {
        return new TokenVerifyResponse(user.getId(), user.getUsername());
    }
}
