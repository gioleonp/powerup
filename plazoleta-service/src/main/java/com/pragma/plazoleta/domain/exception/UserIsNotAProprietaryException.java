package com.pragma.plazoleta.domain.exception;

public class UserIsNotAProprietaryException extends RuntimeException{
    public UserIsNotAProprietaryException() {
        super("EL USUARIO NO ES UN PROPIETARIO");
    }
}
