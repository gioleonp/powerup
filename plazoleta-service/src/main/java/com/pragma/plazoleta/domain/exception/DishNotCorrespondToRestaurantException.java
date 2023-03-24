package com.pragma.plazoleta.domain.exception;

public class DishNotCorrespondToRestaurantException extends RuntimeException{
    public DishNotCorrespondToRestaurantException() {
        super("DISH NOT CORRESPOND TO THE RESTAURANT");
    }
}
