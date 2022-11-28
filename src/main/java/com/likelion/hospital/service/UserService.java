package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;

public interface UserService {
    UserResponse join(SignUpDTO dto);

    UserResponse login(SignInDTO dto);
}