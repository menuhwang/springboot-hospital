package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findByHospitalNameContains(String name, Pageable pageable);
    Page<Hospital> findByFullAddressContainsOrFullAddressContains(String city1, String city2, Pageable pageable);
}
