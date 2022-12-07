package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.visit.VisitRequest;
import com.likelion.hospital.domain.dto.visit.VisitResponse;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitApiController {
    private final VisitService visitService;

    @PostMapping("")
    public VisitResponse create(@RequestBody VisitRequest visitRequest, @AuthenticationPrincipal User me) {
        return visitService.create(visitRequest, me.getId());
    }

    @GetMapping("")
    public List<VisitResponse> findAll() {
        return visitService.findAll();
    }

    @GetMapping("/users/{userId}")
    public List<VisitResponse> findByUser(@PathVariable("userId") Long userId) {
        return visitService.findAllByUser(userId);
    }

    @GetMapping("/hospitals/{hospitalId}")
    public List<VisitResponse> findByHospital(@PathVariable("hospitalId") Integer hospitalId) {
        return visitService.findAllByHospital(hospitalId);
    }
}
