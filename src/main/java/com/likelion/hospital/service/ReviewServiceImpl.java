package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.Review;
import com.likelion.hospital.exception.HospitalNotFoundException;
import com.likelion.hospital.repository.HospitalRepository;
import com.likelion.hospital.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResDTO create(Integer boardId, ReviewReqDTO dto) {
        Hospital hospital = hospitalRepository.findById(boardId).orElseThrow(HospitalNotFoundException::new);
        Review review = dto.toEntity();
        hospital.addReview(review);
        Review saved = reviewRepository.save(review);
        return ReviewResDTO.of(saved);
    }

    @Override
    public ReviewResDTO findById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(HospitalNotFoundException::new);
        return ReviewResDTO.of(review);
    }

    @Override
    public List<ReviewResDTO> findByHospital(Integer hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(HospitalNotFoundException::new);
        List<Review> reviews = reviewRepository.findByHospital(hospital);
        return reviews.stream().map(ReviewResDTO::of).collect(Collectors.toList());
    }
}
