package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;

import java.util.List;

public interface ReviewService {
    ReviewResDTO create(Integer boardId, ReviewReqDTO dto);

    ReviewResDTO findById(Long id);

    List<ReviewResDTO> findByHospital(Integer hospitalId);
}
