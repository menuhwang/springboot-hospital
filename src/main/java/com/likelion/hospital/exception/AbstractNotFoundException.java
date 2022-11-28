package com.likelion.hospital.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractNotFoundException extends AbstractHttpResponseException {
    public AbstractNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
