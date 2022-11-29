package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignInToken;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class SignApiController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> join(@RequestBody SignUpDTO dto) {
        return ResponseEntity.ok(userService.join(dto));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInToken> login(@RequestBody SignInDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
