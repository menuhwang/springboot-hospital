package com.likelion.hospital.domain.entity.dto.hospital;

import lombok.Getter;

@Getter
public class HospitalResDTO {
    private String hospitalName;
    private String fullAddress;
    private String roadNameAddress;
    private Integer healthcareProviderCount;
    private Integer patientRoomCount;
    private Float totalAreaSize;
    private Boolean shutDown;
}
