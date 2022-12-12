package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.visit.VisitRequest;
import com.likelion.hospital.domain.dto.visit.VisitResponse;

import java.util.List;

public interface VisitService {
    VisitResponse create(VisitRequest visitRequest, String username);

    List<VisitResponse> findAll();

    List<VisitResponse> findAllByUser(Long userId);

    List<VisitResponse> findAllByUser(String username);

    List<VisitResponse> findAllByHospital(Integer hospitalId);
}
