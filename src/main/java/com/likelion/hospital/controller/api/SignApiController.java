package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.user.*;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public TokenVerifyResponse verify(@AuthenticationPrincipal User me) {
        return TokenVerifyResponse.of(me);
    }
}
