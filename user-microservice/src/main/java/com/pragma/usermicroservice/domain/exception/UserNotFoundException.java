package com.pragma.usermicroservice.domain.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email){
        super("USER NOT FOUND: " + email);
    }
}
