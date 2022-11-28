package com.likelion.hospital.exception.notfound;

import com.likelion.hospital.exception.AbstractHttpResponseException;
import org.springframework.http.HttpStatus;

public abstract class AbstractNotFoundException extends AbstractHttpResponseException {
    public AbstractNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
