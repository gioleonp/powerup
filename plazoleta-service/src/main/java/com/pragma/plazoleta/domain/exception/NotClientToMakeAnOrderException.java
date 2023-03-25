package com.pragma.plazoleta.domain.exception;

public class NotClientToMakeAnOrderException extends RuntimeException{
    public NotClientToMakeAnOrderException() {
        super("USER HAS NOT RIGHTS TO PERFORM THIS ACTION");
    }
}
