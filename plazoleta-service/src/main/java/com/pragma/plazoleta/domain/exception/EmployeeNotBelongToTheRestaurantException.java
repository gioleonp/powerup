package com.pragma.plazoleta.domain.exception;

public class EmployeeNotBelongToTheRestaurantException extends RuntimeException{
    public EmployeeNotBelongToTheRestaurantException() {
        super("EL EMPLEADO NO HACE PARTE DEL RESTAURANTE PARA REALIZAR ESA ACCION");
    }
}
