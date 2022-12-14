package com.likelion.hospital.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    private String content;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
