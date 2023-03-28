package com.pragma.plazoleta.domain.exception;

public class EmployeeIsNotOrderChefException extends RuntimeException{
    public EmployeeIsNotOrderChefException() {
        super("EMPLOYEE IS NOT THE CHEF OF THE ORDER");
    }
}
