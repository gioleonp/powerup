package com.pragma.plazoleta.domain.exception;

public class DishNotCorrespondToRestaurantException extends RuntimeException{
    public DishNotCorrespondToRestaurantException() {
        super("EL PLATO NO CORRESPONDE AL RESTUARANTE");
    }
}
