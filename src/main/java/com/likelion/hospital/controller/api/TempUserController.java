package com.likelion.hospital.controller.api;


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
@RequestMapping("/tempUsers")
@RequiredArgsConstructor
public class TempUserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponse> createUser(@RequestBody SignUpDTO dto){
        return ResponseEntity.ok(userService.join(dto));
    }
}
