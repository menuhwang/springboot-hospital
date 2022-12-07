package com.likelion.hospital.service;

import com.likelion.hospital.domain.dto.visit.VisitRequest;
import com.likelion.hospital.domain.dto.visit.VisitResponse;
import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.domain.entity.Visit;
import com.likelion.hospital.exception.notfound.HospitalNotFoundException;
import com.likelion.hospital.exception.notfound.UserNotFoundException;
import com.likelion.hospital.repository.HospitalRepository;
import com.likelion.hospital.repository.UserRepository;
import com.likelion.hospital.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public VisitResponse create(VisitRequest visitRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Hospital hospital = hospitalRepository.findById(visitRequest.getHospitalId()).orElseThrow(HospitalNotFoundException::new);
        Visit visit = visitRequest.toEntity(user, hospital);
        return VisitResponse.of(visitRepository.save(visit));
    }

    @Override
    public List<VisitResponse> findAll() {
        return visitRepository.findAll().stream().map(VisitResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<VisitResponse> findAllByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return visitRepository.findByUser(user).stream().map(VisitResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<VisitResponse> findAllByHospital(Integer hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(HospitalNotFoundException::new);
        return visitRepository.findByHospital(hospital).stream().map(VisitResponse::of).collect(Collectors.toList());
    }
}
