package com.likelion.hospital.repository;

import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUser(User user);

    List<Visit> findByHospital(Hospital hospital);
}
