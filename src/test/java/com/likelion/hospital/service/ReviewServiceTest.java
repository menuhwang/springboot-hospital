package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.review.ReviewReqDTO;
import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.Review;
import com.likelion.hospital.repository.HospitalRepository;
import com.likelion.hospital.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class ReviewServiceTest {
    private final HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);
    private final ReviewRepository reviewRepository = Mockito.mock(ReviewRepository.class);
    private final ReviewService reviewService = new ReviewServiceImpl(hospitalRepository, reviewRepository);

    @Test
    void create() {
        Integer boardId = 1;
        ReviewReqDTO reviewReqDTO = ReviewReqDTO.builder()
                .author("author")
                .content("content")
                .build();

        Hospital hospital = Hospital.builder()
                .id(1)
                .openServiceName("의원")
                .openLocalGovernmentCode(3620000)
                .managementNumber("PHMA119993620020041100004")
                .licenseDate(LocalDateTime.of(1999, 6, 12, 0, 0, 0))
                .businessStatus(1)
                .businessStatusCode(13)
                .phone("062-515-2875")
                .fullAddress("광주광역시 북구 풍향동 565번지 4호 3층")
                .roadNameAddress("광주광역시 북구 동문대로 24, 3층 (풍향동)")
                .hospitalName("효치과의원")
                .businessTypeName("치과의원")
                .healthcareProviderCount(1)
                .patientRoomCount(1)
                .totalNumberOfBeds(0)
                .totalAreaSize(52.29F)
                .build();

        Review review = reviewReqDTO.toEntity();
        review.setHospital(hospital);

        given(hospitalRepository.findById(1)).willReturn(Optional.of(hospital));
        given(reviewRepository.save(any(Review.class))).willReturn(review);

        ReviewResDTO result = reviewService.create(boardId, reviewReqDTO);

        assertEquals(review.getAuthor(), result.getAuthor());
        assertEquals(review.getContent(), result.getContent());
    }

    @Test
    void findById() {
        ReviewResDTO expected = ReviewResDTO.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        Review review = Review.builder()
                .id(1L)
                .author("author")
                .content("content")
                .build();

        given(reviewRepository.findById(1L)).willReturn(Optional.of(review));

        ReviewResDTO result = reviewService.findById(1L);

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getAuthor(), result.getAuthor());
        assertEquals(expected.getContent(), result.getContent());
    }

    @Test
    void findByHospital() {
        Integer HOSPITAL_ID = 1;
        List<ReviewResDTO> reviewResDTOList = new ArrayList<>();
        List<Review> reviewList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            reviewList.add(Review.builder()
                    .id((long) i)
                    .author("author" + i)
                    .content("content" + i)
                    .build());
            reviewResDTOList.add(ReviewResDTO.builder()
                    .id((long) i)
                    .author("author" + i)
                    .content("content" + i)
                    .build());
        }

        Hospital mockHospital = new Hospital();

        given(hospitalRepository.findById(HOSPITAL_ID)).willReturn(Optional.of(mockHospital));
        given(reviewRepository.findByHospital(any(Hospital.class))).willReturn(reviewList);

        List<ReviewResDTO> resultList = reviewService.findByHospital(HOSPITAL_ID);

        for (int i = 0; i < resultList.size(); i++) {
            ReviewResDTO expected = reviewResDTOList.get(i);
            ReviewResDTO result = resultList.get(i);
            assertEquals(expected.getId(), result.getId());
            assertEquals(expected.getAuthor(), result.getAuthor());
            assertEquals(expected.getContent(), result.getContent());
        }
    }
}