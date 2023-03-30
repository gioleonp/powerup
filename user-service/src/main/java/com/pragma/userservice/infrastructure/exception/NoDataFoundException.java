package com.pragma.userservice.infrastructure.exception;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super("NO DATA FOUND FOR " + message);
    }
}
