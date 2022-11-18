package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.hospital.HospitalResDTO;
import com.likelion.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalApiController {
    private final HospitalService hospitalService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResDTO> findById(@PathVariable("id") Integer id) {
        log.info("병원 조회 id:{}", id);
        return ResponseEntity.ok(hospitalService.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<HospitalResDTO>> findAll(@Nullable @RequestParam("city") String city, @PageableDefault Pageable pageable) {
        log.info("병원 검색 city:{}, page:{}", city, pageable);
        Page<HospitalResDTO> page = (city == null) ? hospitalService.findAll(pageable) : hospitalService.findByCity(city, pageable);
        return ResponseEntity.ok(page);
    }

//    @GetMapping("/{name}")
//    public ResponseEntity<Page<HospitalResDTO>> searchByName(@PathVariable("name") String name, @PageableDefault Pageable pageable) {
//        return ResponseEntity.ok(hospitalService.searchByName(name, pageable));
//    }
}
