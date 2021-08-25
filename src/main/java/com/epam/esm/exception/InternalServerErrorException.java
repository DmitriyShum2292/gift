package com.epam.esm.exception;

import java.sql.SQLException;

public class InternalServerErrorException extends SQLException {

    private int id;

    public InternalServerErrorException(String reason) {
        super(reason);
    }

    public InternalServerErrorException(String reason,int id) {
        super(reason);
        this.id = id;
    }

    public InternalServerErrorException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
