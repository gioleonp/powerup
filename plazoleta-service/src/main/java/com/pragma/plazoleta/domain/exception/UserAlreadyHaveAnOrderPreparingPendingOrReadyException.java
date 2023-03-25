package com.pragma.plazoleta.domain.exception;

public class UserAlreadyHaveAnOrderPreparingPendingOrReadyException extends RuntimeException{
    public UserAlreadyHaveAnOrderPreparingPendingOrReadyException() {
        super("USER ALREADY HAVE AN ORDER PENDING, IN PREPARING OR READY");
    }
}
