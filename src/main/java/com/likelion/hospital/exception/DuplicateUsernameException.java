package com.likelion.hospital.exception;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends AbstractHttpResponseException {
    public DuplicateUsernameException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");
    }
}
