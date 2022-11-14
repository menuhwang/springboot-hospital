package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void findByBusinessTypeNameIn() {
        List<String> query = new ArrayList<>(Arrays.asList("보건진료소", "보건지소", "보건소"));
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(query);
        for(Hospital hospital: hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }
}