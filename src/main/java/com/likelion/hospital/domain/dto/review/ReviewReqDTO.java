package com.likelion.hospital.domain.dto.review;

import com.likelion.hospital.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ReviewReqDTO {
    private String author;
    private String content;

    public Review toEntity() {
        return Review.builder()
                .author(author)
                .content(content)
                .build();
    }
}
