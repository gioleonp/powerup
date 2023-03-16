package com.pragma.userservice.domain.exception;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email){
        super("USER NOT FOUND: " + email);
    }
}
