package com.likelion.hospital.domain.dto.review;

import com.likelion.hospital.domain.entity.Review;
import com.likelion.hospital.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor // 스웨거 바인딩 이슈 때문에 추가.
@Getter
public class ReviewReqDTO {
    private String content;

    public Review toEntity(User author) {
        return Review.builder()
                .author(author)
                .content(content)
                .build();
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .build();
    }
}
