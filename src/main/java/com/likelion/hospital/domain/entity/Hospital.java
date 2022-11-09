package com.likelion.hospital.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "nation_wide_hospitals")
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

    public boolean isShutDown() {
        return businessStatus == 3 || businessStatusCode == 3;
    }
}
