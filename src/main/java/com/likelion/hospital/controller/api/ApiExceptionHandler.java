package com.likelion.hospital.controller.api;

import com.likelion.hospital.domain.ErrorResponse;
import com.likelion.hospital.exception.AbstractNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = AbstractNotFoundException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundExceptionHandler(AbstractNotFoundException exception) {
        return ResponseEntity.status(exception.getStatus().value()).body(exception.toErrorResponse());
    }
}
