package com.likelion.hospital.domain.dto.visit;

import com.likelion.hospital.domain.dto.hospital.HospitalResDTO;
import com.likelion.hospital.domain.dto.user.UserResponse;
import com.likelion.hospital.domain.entity.Visit;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VisitResponse {
    private Long id;
    private UserResponse user;
    private HospitalResDTO hospital;
    private String disease;
    private Integer expense;
    private LocalDateTime visitedDatetime;

    public VisitResponse() {
    }

    @Builder
    public VisitResponse(Long id, UserResponse user, HospitalResDTO hospital, String disease, Integer expense, LocalDateTime visitedDatetime) {
        this.id = id;
        this.user = user;
        this.hospital = hospital;
        this.disease = disease;
        this.expense = expense;
        this.visitedDatetime = visitedDatetime;
    }

    public static VisitResponse of(Visit visit) {
        return VisitResponse.builder()
                .id(visit.getId())
                .user(UserResponse.of(visit.getUser()))
                .hospital(HospitalResDTO.of(visit.getHospital()))
                .disease(visit.getDisease())
                .expense(visit.getExpense())
                .visitedDatetime(visit.getCreatedDatetime())
                .build();
    }
}
