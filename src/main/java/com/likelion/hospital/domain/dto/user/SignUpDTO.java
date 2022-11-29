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
    private String userName;
    private String password;
    private String emailAddress;

    public void encodePassword(String encoded) {
        this.password = encoded;
    }

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .emailAddress(emailAddress)
                .build();
    }
}
