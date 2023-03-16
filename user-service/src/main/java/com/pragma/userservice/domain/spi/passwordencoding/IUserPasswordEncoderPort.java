package com.pragma.userservice.domain.spi.passwordencoding;

public interface IUserPasswordEncoderPort {
    String encode(String password);
}
