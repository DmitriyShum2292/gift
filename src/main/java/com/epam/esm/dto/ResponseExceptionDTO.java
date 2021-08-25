package com.epam.esm.dto;

import org.springframework.http.HttpStatus;

public class ResponseExceptionDTO {

    private String errorMessage;
    private int errorCode;
    private int httpStatus;

    public ResponseExceptionDTO(String errorMessage, int errorCode, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus.value();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}