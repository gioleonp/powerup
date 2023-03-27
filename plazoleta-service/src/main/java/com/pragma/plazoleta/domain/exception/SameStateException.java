package com.pragma.plazoleta.domain.exception;

public class SameStateException extends RuntimeException {
    public SameStateException() {
        super("YOU MUST TO CHANGE THE STATE OF YOUR DISH TO A DIFFERENT ONE");
    }
}
