package com.pragma.plazoleta.domain.exception;

public class SameStateException extends RuntimeException {
    public SameStateException() {
        super("DEBE CAMBIAR EL ESTADO DEL PLATO A UNO DIFERENTE");
    }
}
