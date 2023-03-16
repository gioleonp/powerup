package com.pragma.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String identificationDocument;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private RoleResponseDto role;
}
