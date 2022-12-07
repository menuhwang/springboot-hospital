package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.visit.VisitRequest;
import com.likelion.hospital.domain.dto.visit.VisitResponse;

import java.util.List;

public interface VisitService {
    VisitResponse create(VisitRequest visitRequest, Long userId);

    List<VisitResponse> findAll();

    List<VisitResponse> findAllByUser(Long userId);

    List<VisitResponse> findAllByHospital(Integer hospitalId);
}
