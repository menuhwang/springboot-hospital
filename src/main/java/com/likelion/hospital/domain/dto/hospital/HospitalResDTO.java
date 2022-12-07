package com.likelion.hospital.domain.dto.hospital;

import com.likelion.hospital.domain.entity.Hospital;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HospitalResDTO {
    private Integer id;
    private String hospitalName;
    private String fullAddress;
    private String roadNameAddress;
    private Integer healthcareProviderCount;
    private Integer patientRoomCount;
    private Float totalAreaSize;
    private Boolean shutDown;

    private HospitalResDTO() {
    }

    @Builder
    public HospitalResDTO(Integer id, String hospitalName, String fullAddress, String roadNameAddress, Integer healthcareProviderCount, Integer patientRoomCount, Float totalAreaSize, Boolean shutDown) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.fullAddress = fullAddress;
        this.roadNameAddress = roadNameAddress;
        this.healthcareProviderCount = healthcareProviderCount;
        this.patientRoomCount = patientRoomCount;
        this.totalAreaSize = totalAreaSize;
        this.shutDown = shutDown;
    }

    public static HospitalResDTO of(Hospital hospital) {
        return HospitalResDTO.builder()
                .id(hospital.getId())
                .hospitalName(hospital.getHospitalName())
                .fullAddress(hospital.getFullAddress())
                .roadNameAddress(hospital.getRoadNameAddress())
                .healthcareProviderCount(hospital.getHealthcareProviderCount())
                .patientRoomCount(hospital.getPatientRoomCount())
                .totalAreaSize(hospital.getTotalAreaSize())
                .shutDown(hospital.isShutDown())
                .build();
    }
}
