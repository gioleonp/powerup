package com.pragma.plazoleta.infrastructure.exception;

public class ProprietaryNotMatchException extends RuntimeException{

    public ProprietaryNotMatchException(){
        super("SOLO EL PROPIERTARIO DEL RESTAURANTE PUEDE CREAR EL PLATO");
    }

}
