package com.pragma.plazoleta.domain.exception;

public class ClientIsNotOrderOwnerException extends RuntimeException{
    public ClientIsNotOrderOwnerException() {
        super("SOLO EL CLIENTE QUE HIZO EL PEDIDO PUEDE INTERACTUAR CON EL.");
    }
}
