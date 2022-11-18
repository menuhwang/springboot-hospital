package com.likelion.hospital.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HospitalTest {
    private Hospital hospital1 = Hospital.builder()
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
    private Hospital hospital2 = Hospital.builder()
            .id(1)
            .openServiceName("의원")
            .openLocalGovernmentCode(3620000)
            .managementNumber("PHMA119993620020041100004")
            .licenseDate(LocalDateTime.of(1999, 6, 12, 0, 0, 0))
            .businessStatus(1)
            .businessStatusCode(3)
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

    @Test
    void isShutDownFalse() {
        assertFalse(hospital1.isShutDown());
    }

    @Test
    void isShutDownTrue() {
        assertTrue(hospital2.isShutDown());
    }
}