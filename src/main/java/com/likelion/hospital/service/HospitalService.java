package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.hospital.HospitalResDTO;
import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalService {
    HospitalResWithReviewDTO findById(Integer id);

    Page<HospitalResDTO> findAll(Pageable pageable);
    Page<HospitalResDTO> findByCity(String city, Pageable pageable);
    Page<HospitalResDTO> searchByName(String name, Pageable pageable);
}
