package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.hospital.HospitalResDTO;
import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.exception.notfound.HospitalNotFoundException;
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
    public HospitalResWithReviewDTO findById(Integer id) {
        Hospital hospital = hospitalRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
        return HospitalResWithReviewDTO.of(hospital);
    }

    @Override
    public Page<HospitalResDTO> findAll(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        return hospitals.map(HospitalResDTO::of);
    }

    @Override
    public Page<HospitalResDTO> findByCity(String city, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findByFullAddressContainsOrRoadNameAddressContains(city, city, pageable);
        return hospitals.map(HospitalResDTO::of);
    }

    @Override
    public Page<HospitalResDTO> searchByName(String name, Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findByHospitalNameContains(name, pageable);
        return hospitals.map(HospitalResDTO::of);
    }
}
