package com.pragma.plazoleta.domain.exception;

public class UserAlreadyHaveAnOrderPreparingPendingOrReadyException extends RuntimeException{
    public UserAlreadyHaveAnOrderPreparingPendingOrReadyException() {
        super("EL USUARIO YA TIENE UN PEDIDO EN PENDIENTE, EN PREPARACION O LISTO");
    }
}
