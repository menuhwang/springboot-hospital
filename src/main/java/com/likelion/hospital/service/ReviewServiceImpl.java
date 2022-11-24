package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.Review;
import com.likelion.hospital.repository.HospitalRepository;
import com.likelion.hospital.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResDTO create(Integer boardId, ReviewReqDTO dto) {
        Hospital hospital = hospitalRepository.findById(boardId).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        Review review = dto.toEntity();
        hospital.addReview(review);
        Review saved = reviewRepository.save(review);
        return ReviewResDTO.of(saved);
    }

    @Override
    public ReviewResDTO findById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 병원을 찾을 수 없습니다."));
        return ReviewResDTO.of(review);
    }
}
