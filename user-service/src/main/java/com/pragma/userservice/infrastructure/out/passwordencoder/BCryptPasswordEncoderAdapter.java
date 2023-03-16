package com.pragma.userservice.infrastructure.out.passwordencoder;

import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
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
