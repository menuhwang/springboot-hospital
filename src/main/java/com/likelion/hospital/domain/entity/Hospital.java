package com.likelion.hospital.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Hospital {
    @Id
    private Integer id;
    private String openServiceName;
    private Integer openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private Integer businessStatus;
    private Integer businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private Integer healthcareProviderCount;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private Float totalAreaSize;
}
