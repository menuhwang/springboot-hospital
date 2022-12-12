package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignInToken;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;

public interface UserService extends UserDetailsService {
    UserResponse join(SignUpDTO dto);

    SignInToken login(SignInDTO dto);

    UserResponse verify(Principal me);
}
