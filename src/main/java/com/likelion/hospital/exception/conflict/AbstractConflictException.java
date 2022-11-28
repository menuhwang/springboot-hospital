package com.likelion.hospital.exception.conflict;

import com.likelion.hospital.exception.AbstractHttpResponseException;
import org.springframework.http.HttpStatus;

public abstract class AbstractConflictException extends AbstractHttpResponseException {
    public AbstractConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
