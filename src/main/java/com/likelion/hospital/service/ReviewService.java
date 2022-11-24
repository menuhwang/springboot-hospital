package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;

public interface ReviewService {
    ReviewResDTO create(Integer boardId, ReviewReqDTO dto);
}
