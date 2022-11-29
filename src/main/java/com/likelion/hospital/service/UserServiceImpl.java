package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.conflict.DuplicateUsernameException;
import com.likelion.hospital.exception.forbidden.SignInForbiddenException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse join(SignUpDTO dto) {
        Optional<User> userOptional = userRepository.findByUserName(dto.getUserName());
        if (userOptional.isPresent()) throw new DuplicateUsernameException();
        dto.encodePassword(passwordEncoder.encode(dto.getPassword()));
        return UserResponse.of(userRepository.save(dto.toEntity()));
    }

    @Override
    public UserResponse login(SignInDTO dto) {
        User user = userRepository.findByUserName(dto.getUserName()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) throw new SignInForbiddenException();
        return UserResponse.of(user);
    }
}
