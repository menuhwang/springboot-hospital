package com.likelion.hospital.domain;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private int code;
    private String type;
    private String message;

    public ErrorResponse(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
