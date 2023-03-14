package com.pragma.usermicroservice.infrastructure.out.passwordencoder;

import com.pragma.usermicroservice.domain.spi.IPasswordEncoder;

public class BCryptPasswordEncoderAdapter implements IPasswordEncoder {


    private BCryptPasswordEncoderAdapter passwordEncoderAdapter =
            new BCryptPasswordEncoderAdapter();

    @Override
    public String decode(String password) {
        return passwordEncoderAdapter.decode(password);
    }

    @Override
    public String encode(String password) {
        return passwordEncoderAdapter.encode(password);
    }
}
