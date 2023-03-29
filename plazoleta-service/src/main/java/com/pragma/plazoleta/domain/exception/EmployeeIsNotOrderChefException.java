package com.pragma.plazoleta.domain.exception;

public class EmployeeIsNotOrderChefException extends RuntimeException{
    public EmployeeIsNotOrderChefException() {
        super("EL EMPLEADO NO ES EL CHEF DEL PEDIDO");
    }
}
