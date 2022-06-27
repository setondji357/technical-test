package com.tenancia.mongotest.exception;

import org.springframework.http.HttpStatus;

public class TenanciaApiException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public TenanciaApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public TenanciaApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}