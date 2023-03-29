package com.pragma.plazoleta.domain.exception;

public class ProprietaryNotMatchException extends RuntimeException{

    public ProprietaryNotMatchException(){
        super("USTED NO ES EL DUEÑO DEL RESTAURANTE");
    }

}
