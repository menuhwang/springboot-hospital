package com.likelion.hospital.service;

import com.likelion.hospital.domain.entity.dto.hospital.HospitalResDTO;

public interface HospitalService {
    HospitalResDTO findById(Integer id);
}
