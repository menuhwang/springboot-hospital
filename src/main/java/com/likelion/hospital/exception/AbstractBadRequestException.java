package com.likelion.hospital.exception;

import org.springframework.http.HttpStatus;

public class AbstractBadRequestException extends AbstractHttpResponseException {
    public AbstractBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
