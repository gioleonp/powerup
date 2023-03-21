package com.pragma.plazoleta.infrastructure.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCredentialsDto {
    private String email;
    private String password;
}
