package com.likelion.hospital.exception.badrequest;

import com.likelion.hospital.exception.AbstractHttpResponseException;
import org.springframework.http.HttpStatus;

public abstract class AbstractBadRequestException extends AbstractHttpResponseException {
    public AbstractBadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
