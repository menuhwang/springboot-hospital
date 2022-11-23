package com.likelion.hospital.domain.dto.hospital;

import com.likelion.hospital.domain.dto.review.ReviewResDTO;
import com.likelion.hospital.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class HospitalResWithReviewDTO {
    private Integer id;
    private String hospitalName;
    private String fullAddress;
    private String roadNameAddress;
    private Integer healthcareProviderCount;
    private Integer patientRoomCount;
    private Float totalAreaSize;
    private Boolean shutDown;
    private List<ReviewResDTO> reviews;

    public static HospitalResWithReviewDTO of(Hospital hospital) {
        return HospitalResWithReviewDTO.builder()
                .id(hospital.getId())
                .hospitalName(hospital.getHospitalName())
                .fullAddress(hospital.getFullAddress())
                .roadNameAddress(hospital.getRoadNameAddress())
                .healthcareProviderCount(hospital.getHealthcareProviderCount())
                .patientRoomCount(hospital.getPatientRoomCount())
                .totalAreaSize(hospital.getTotalAreaSize())
                .shutDown(hospital.isShutDown())
                .reviews(hospital.getReviews().stream().map(ReviewResDTO::of).collect(Collectors.toList()))
                .build();
    }
}
