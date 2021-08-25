package com.epam.esm.dto;

import org.springframework.http.HttpStatus;

public class ResponseSuccessDTO {

    private String message;
    private HttpStatus httpStatus;

    public ResponseSuccessDTO(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
