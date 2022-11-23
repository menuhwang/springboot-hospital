package com.likelion.hospital.domain.dto.review;

import com.likelion.hospital.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ReviewResDTO {
    private Long id;
    private String author;
    private String content;

    public static ReviewResDTO of(Review review) {
        return ReviewResDTO.builder()
                .id(review.getId())
                .author(review.getAuthor())
                .content(review.getContent())
                .build();
    }
}
