package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.DuplicateUsernameException;
import com.likelion.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse join(SignUpDTO dto) {
        Optional<User> userOptional = userRepository.findByUserName(dto.getUserName());
        if (userOptional.isPresent()) throw new DuplicateUsernameException();
        return UserResponse.of(userRepository.save(dto.toEntity()));
    }
}
