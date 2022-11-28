package com.likelion.hospital.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractForbiddenException extends AbstractHttpResponseException {
    public AbstractForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
