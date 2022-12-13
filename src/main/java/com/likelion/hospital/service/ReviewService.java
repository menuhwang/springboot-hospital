package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;

import java.security.Principal;
import java.util.List;

public interface ReviewService {
    ReviewResDTO create(Integer boardId, ReviewReqDTO dto, Principal me);

    ReviewResDTO findById(Long id);

    List<ReviewResDTO> findByHospital(Integer hospitalId);
}
