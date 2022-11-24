package com.likelion.hospital.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Builder.Default
    @OneToMany(mappedBy = "hospital")
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        review.setHospital(this);
        this.reviews.add(review);
    }

    public boolean isShutDown() {
        return businessStatus == 3 || businessStatusCode == 3;
    }
}
