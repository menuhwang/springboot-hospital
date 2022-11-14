package com.likelion.hospital.domain.dto.hospital;

import com.likelion.hospital.domain.entity.Hospital;
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

    public HospitalResDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.hospitalName = hospital.getHospitalName();
        this.fullAddress = hospital.getFullAddress();
        this.roadNameAddress = hospital.getRoadNameAddress();
        this.healthcareProviderCount = hospital.getHealthcareProviderCount();
        this.patientRoomCount = hospital.getPatientRoomCount();
        this.totalAreaSize = hospital.getTotalAreaSize();
        this.shutDown = hospital.isShutDown();
    }
}
