package com.pragma.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String documentoDeIdentidad;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private RoleResponseDto rol;
}
