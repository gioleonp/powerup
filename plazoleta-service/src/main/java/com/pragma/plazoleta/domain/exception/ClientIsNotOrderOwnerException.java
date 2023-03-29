package com.pragma.plazoleta.domain.exception;

public class ClientIsNotOrderOwnerException extends RuntimeException{
    public ClientIsNotOrderOwnerException() {
        super("ONLY THE CLIENT OWNER OF THE ORDER CAN CANCEL IT.");
    }
}
