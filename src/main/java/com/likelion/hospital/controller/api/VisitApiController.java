package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.visit.VisitRequest;
import com.likelion.hospital.domain.dto.visit.VisitResponse;
import com.likelion.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitApiController {
    private final VisitService visitService;

    @PostMapping("")
    public VisitResponse create(@RequestBody VisitRequest visitRequest, Principal me) {
        return visitService.create(visitRequest, me.getName());
    }

    @GetMapping("")
    public List<VisitResponse> findAll() {
        return visitService.findAll();
    }

    @GetMapping("/users/{userId}")
    public List<VisitResponse> findByUser(@PathVariable("userId") Long userId) {
        return visitService.findAllByUser(userId);
    }

    @GetMapping("/me")
    public List<VisitResponse> findMyVisit(Principal me) {
        return visitService.findAllByUser(me.getName());
    }

    @GetMapping("/hospitals/{hospitalId}")
    public List<VisitResponse> findByHospital(@PathVariable("hospitalId") Integer hospitalId) {
        return visitService.findAllByHospital(hospitalId);
    }
}
