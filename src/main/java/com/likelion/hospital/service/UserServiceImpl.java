package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignInToken;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.exception.conflict.DuplicateUsernameException;
import com.likelion.hospital.exception.forbidden.SignInForbiddenException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.UserRepository;
import com.likelion.hospital.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserResponse join(SignUpDTO dto) {
        Optional<User> userOptional = userRepository.findByUsername(dto.getUsername());
        if (userOptional.isPresent()) throw new DuplicateUsernameException();
        return UserResponse.of(userRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword()))));
    }

    @Override
    public SignInToken login(SignInDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) throw new SignInForbiddenException();
        return new SignInToken(jwtTokenUtil.generateToken(user));
    }

    @Override
    public UserResponse verify(Principal me) {
        User user = userRepository.findByUsername(me.getName()).orElseThrow(UserNotFoundException::new);
        return UserResponse.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("유저 정보 없음. username:" + username));
        return user;
    }
}
