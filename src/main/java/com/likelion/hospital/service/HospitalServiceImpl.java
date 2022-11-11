package com.likelion.hospital.service;

import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.dto.hospital.HospitalResDTO;
import com.likelion.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public HospitalResDTO findById(Integer id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        return new HospitalResDTO(hospital);
    }

    @Override
    public Page<HospitalResDTO> findAll(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        return hospitals.map(HospitalResDTO::new);
    }

    @Override
    public Page<HospitalResDTO> searchByName(String name, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findByHospitalNameContains(name, pageable);
        return hospitals.map(HospitalResDTO::new);
    }
}
