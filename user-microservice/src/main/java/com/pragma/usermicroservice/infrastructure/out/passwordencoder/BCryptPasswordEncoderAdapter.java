package com.pragma.usermicroservice.infrastructure.out.passwordencoder;

import com.pragma.usermicroservice.domain.spi.IUserPasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class BCryptPasswordEncoderAdapter implements IUserPasswordEncoderPort {
    private PasswordEncoder passwordEncoderAdapter = new BCryptPasswordEncoder();

    @Override
    public String encode(String password) {
        return passwordEncoderAdapter.encode(password);
    }
}
