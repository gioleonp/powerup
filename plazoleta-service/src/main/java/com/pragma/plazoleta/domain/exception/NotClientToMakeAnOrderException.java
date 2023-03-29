package com.pragma.plazoleta.domain.exception;

public class NotClientToMakeAnOrderException extends RuntimeException {
    public NotClientToMakeAnOrderException() {
        super("EL USUARIO NO ES UN CLIENTE PARA REALIZAR DICHA ACCION");
    }
}
