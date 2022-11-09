package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.entity.dto.hospital.HospitalResDTO;
import com.likelion.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(hospitalService.findById(id));
    }
}
