package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    /*
    경기도 수원시 피부과
    SELECT * FROM nation_wide_hospitals
         WHERE (full_address LIKE '경기도 수원시%' OR road_name_address LIKE '경기도 수원시%')
           AND nation_wide_hospitals.hospital_name LIKE '%피부%';

    특정 구의 보건 진료소, 보건지소, 보건소 모두 찾기
    SELECT * FROM nation_wide_hospitals WHERE (full_address LIKE '인천광역시%' OR road_name_address LIKE '인천광역시%')
           AND business_type_name LIKE '%보건%';
    */
    List<Hospital> findByFullAddressContainsAndBusinessTypeNameIn(String address, List<String> types);
    List<Hospital> findByBusinessTypeNameIn(List<String> types);

    /*
    병상 수가 10개 이상 20개 이하인 병원
    SELECT * FROM nation_wide_hospitals WHERE total_number_of_beds BETWEEN 10 AND 20;
    */
    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(Integer n1, Integer n2);
    Page<Hospital> findByHospitalNameContains(String name, Pageable pageable);
    Page<Hospital> findByFullAddressContainsOrFullAddressContains(String city1, String city2, Pageable pageable);
}
