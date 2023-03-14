package com.pragma.usermicroservice.domain.spi;

public interface IUserPasswordEncoderPort {
    String encode(String password);
}
