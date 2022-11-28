package com.likelion.hospital.domain.dto.user;

import com.likelion.hospital.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpDTO {
    private String userName;
    private String password;
    private String emailAddress;

    public User toEntity() {
        return User.builder()
                .userName(userName)
                .password(password)
                .emailAddress(emailAddress)
                .build();
    }
}
