package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    void findByBusinessTypeNameIn() {
        List<String> types = new ArrayList<>(Arrays.asList("보건진료소", "보건지소", "보건소"));
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(types);
        for(Hospital hospital: hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    void findByAddressContainsAndBusinessTypNameIn() {
        List<String> types = new ArrayList<>(Arrays.asList("보건진료소", "보건지소", "보건소"));
        String address = "인천광역시";
        List<Hospital> hospitals = hospitalRepository.findByFullAddressContainsAndBusinessTypeNameIn(address, types);
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    void findByPatientRoomCountBetweenOrderByPatientRoomCountDesc() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(10, 19);
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }
}