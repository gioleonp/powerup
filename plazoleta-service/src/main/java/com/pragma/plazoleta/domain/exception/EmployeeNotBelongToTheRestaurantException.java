package com.pragma.plazoleta.domain.exception;

public class EmployeeNotBelongToTheRestaurantException extends RuntimeException{
    public EmployeeNotBelongToTheRestaurantException() {
        super("EMPLOYEE NOT BELONGS TO THE RESTAURANT TO MAKE THAT ACTION");
    }
}
