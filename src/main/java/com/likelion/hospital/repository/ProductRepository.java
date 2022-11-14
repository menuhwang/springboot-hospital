package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /*
    경기도 수원시 피부과
    SELECT * FROM nation_wide_hospitals
         WHERE (full_address LIKE '경기도 수원시%' OR road_name_address LIKE '경기도 수원시%')
           AND nation_wide_hospitals.hospital_name LIKE '%피부%';
    */
}
