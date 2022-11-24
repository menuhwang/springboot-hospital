package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewApiController {
    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }
}
