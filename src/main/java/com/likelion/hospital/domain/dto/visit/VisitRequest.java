package com.likelion.hospital.domain.dto.visit;

import com.likelion.hospital.domain.entity.Hospital;
import com.likelion.hospital.domain.entity.User;
import com.likelion.hospital.domain.entity.Visit;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VisitRequest {
    private Integer hospitalId;
    private String disease;
    private Integer expense;

    private VisitRequest() {
    }

    @Builder
    public VisitRequest(Integer hospitalId, String disease, Integer expense) {
        this.hospitalId = hospitalId;
        this.disease = disease;
        this.expense = expense;
    }

    public Visit toEntity(User user, Hospital hospital) {
        return Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(disease)
                .expense(expense)
                .build();
    }
}
