package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.ErrorResponse;
import com.likelion.hospital.exception.AbstractHttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = AbstractHttpResponseException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(AbstractHttpResponseException exception) {
        return ResponseEntity.status(exception.getStatus().value()).body(exception.toErrorResponse());
    }
}
