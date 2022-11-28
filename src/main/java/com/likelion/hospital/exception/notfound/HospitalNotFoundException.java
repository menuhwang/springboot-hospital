package com.likelion.hospital.exception.notfound;

public class HospitalNotFoundException extends AbstractNotFoundException {
    public HospitalNotFoundException() {
        super("해당 병원을 찾을 수 없습니다.");
    }
}
