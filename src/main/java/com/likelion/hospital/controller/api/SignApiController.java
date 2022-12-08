package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.user.SignInDTO;
import com.likelion.hospital.domain.dto.user.SignInToken;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info("로그인 시도 ID:{}", dto.getUsername());
        SignInToken signInToken = userService.login(dto);
        ResponseCookie cookie = ResponseCookie.from("token", signInToken.getToken())
                .httpOnly(false)
                .path("/")
                .build();
        return ResponseEntity.status(200)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(signInToken);
    }

    @PostMapping("/signout")
    public ResponseEntity<Void> logout() {
        ResponseCookie delCookie = ResponseCookie.from("token", "")
                .httpOnly(false)
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.status(204)
                .header(HttpHeaders.SET_COOKIE, delCookie.toString())
                .build();
    }

    @PostMapping("/me")
    public UserResponse verify(@AuthenticationPrincipal User me) {
        return UserResponse.of(me);
    }
}
