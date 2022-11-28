package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.Response;
import com.likelion.hospital.domain.dto.user.SignUpDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public Response<UserResponse> join(@RequestBody SignUpDTO dto) {
        return Response.success(userService.join(dto));
    }
}
