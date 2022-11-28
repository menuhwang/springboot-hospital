package com.likelion.hospital.exception;

import com.likelion.hospital.domain.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractHttpResponseException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public AbstractHttpResponseException(HttpStatus errorStatus, String message) {
        super(message);
        this.status = errorStatus;
        this.message = message;
    }

    public ErrorResponse toErrorResponse() {
        return new ErrorResponse(
                this.status.value(),
                this.status.getReasonPhrase(),
                this.message
        );
    }
}
