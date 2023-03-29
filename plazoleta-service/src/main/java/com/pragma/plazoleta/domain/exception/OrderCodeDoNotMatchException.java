package com.pragma.plazoleta.domain.exception;

public class OrderCodeDoNotMatchException extends RuntimeException {
    public OrderCodeDoNotMatchException() {
        super("ORDER CODE DO NOT MATCH");
    }
}
