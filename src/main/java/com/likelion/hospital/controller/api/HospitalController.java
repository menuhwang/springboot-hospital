package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.entity.dto.hospital.HospitalResDTO;
import com.likelion.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(hospitalService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<HospitalResDTO>> searchByName(@RequestParam("name") String name, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(hospitalService.searchByName(name, pageable));
    }
}
