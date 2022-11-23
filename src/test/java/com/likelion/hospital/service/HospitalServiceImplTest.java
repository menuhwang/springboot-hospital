package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.hospital.HospitalResDTO;
import com.likelion.hospital.domain.dto.hospital.HospitalResWithReviewDTO;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

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
        HospitalResWithReviewDTO result = hospitalService.findById(1);

        // then
        Mockito.verify(hospitalRepository).findById(1);
        assertHospital(hospital, result);
    }

    @Test
    void findAll() {
        //given
        List<Hospital> hospitalList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            hospitalList.add(Hospital.builder()
                    .id(i)
                    .openServiceName("의원")
                    .openLocalGovernmentCode(3620000)
                    .managementNumber("PHMA" + i)
                    .licenseDate(LocalDateTime.of(1999, 6, 12, 0, 0, 0))
                    .businessStatus(1)
                    .businessStatusCode(13)
                    .phone("062-515-287" + i)
                    .fullAddress("주소"+i)
                    .roadNameAddress("도로명주소"+i)
                    .hospitalName("의원"+i)
                    .businessTypeName("치과의원")
                    .healthcareProviderCount(1)
                    .patientRoomCount(1)
                    .totalNumberOfBeds(0)
                    .totalAreaSize(52.29F)
                    .build());
        }
        Page<Hospital> page = new PageImpl<>(hospitalList);

        given(hospitalRepository.findAll(any(Pageable.class))).willReturn(page);

        Page<HospitalResDTO> result = hospitalService.findAll(PageRequest.of(0, 10));

        assertPage(page, result);
    }

    private void assertPage(Page<Hospital> expected, Page<HospitalResDTO> actual) {
        assertEquals(expected.getTotalPages(), expected.getTotalPages());
        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getSize(), actual.getSize());
        List<Hospital> hospitalList = expected.getContent();
        List<HospitalResDTO> hospitalResDTOList = actual.getContent();
        for (int i = 0; i < expected.getSize(); i++) {
            assertHospital(hospitalList.get(i), hospitalResDTOList.get(i));
        }
    }

    private void assertHospital(Hospital expected, HospitalResDTO actual) {
        assertEquals(expected.getHospitalName(), actual.getHospitalName());
        assertEquals(expected.getFullAddress(), actual.getFullAddress());
        assertEquals(expected.getRoadNameAddress(), actual.getRoadNameAddress());
        assertEquals(expected.getHealthcareProviderCount(), actual.getHealthcareProviderCount());
        assertEquals(expected.getPatientRoomCount(), actual.getPatientRoomCount());
        assertEquals(expected.getTotalAreaSize(), actual.getTotalAreaSize());
        assertEquals(expected.isShutDown(), actual.getShutDown());
    }

    private void assertHospital(Hospital expected, HospitalResWithReviewDTO actual) {
        assertEquals(expected.getHospitalName(), actual.getHospitalName());
        assertEquals(expected.getFullAddress(), actual.getFullAddress());
        assertEquals(expected.getRoadNameAddress(), actual.getRoadNameAddress());
        assertEquals(expected.getHealthcareProviderCount(), actual.getHealthcareProviderCount());
        assertEquals(expected.getPatientRoomCount(), actual.getPatientRoomCount());
        assertEquals(expected.getTotalAreaSize(), actual.getTotalAreaSize());
        assertEquals(expected.isShutDown(), actual.getShutDown());
    }
}