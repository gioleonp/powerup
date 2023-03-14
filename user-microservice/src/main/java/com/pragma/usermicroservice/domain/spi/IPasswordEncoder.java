package com.pragma.usermicroservice.domain.spi;

public interface IPasswordEncoder {

    String decode(String password);
    String encode(String password);
}
