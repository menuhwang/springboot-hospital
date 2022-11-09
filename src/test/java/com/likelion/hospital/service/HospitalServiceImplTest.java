package com.likelion.hospital.service;

import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.dto.hospital.HospitalResDTO;
import com.likelion.hospital.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceImplTest {
    private final HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);
    private final HospitalService hospitalService = new HospitalServiceImpl(hospitalRepository);

    @Test
    void findById() {
        // given
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
        // when
        Mockito.when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital));
        HospitalResDTO result = hospitalService.findById(1);

        // then
        Mockito.verify(hospitalRepository).findById(1);
        assertEquals(hospital.getHospitalName(), result.getHospitalName());
        assertEquals(hospital.getFullAddress(), result.getFullAddress());
        assertEquals(hospital.getRoadNameAddress(), result.getRoadNameAddress());
        assertEquals(hospital.getHealthcareProviderCount(), result.getHealthcareProviderCount());
        assertEquals(hospital.getPatientRoomCount(), result.getPatientRoomCount());
        assertEquals(hospital.getTotalAreaSize(), result.getTotalAreaSize());
        assertEquals(hospital.isShutDown(), result.getShutDown());
    }
}