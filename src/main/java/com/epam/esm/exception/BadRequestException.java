package com.epam.esm.exception;

public class BadRequestException extends RuntimeException{
    private int  id;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, int id) {
        super(message);
        this.id = id;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
