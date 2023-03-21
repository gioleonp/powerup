package com.pragma.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseWithPasswordDto {
    private Long id;
    private String documentoDeIdentidad;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String contrasenia;
    private RoleResponseDto rol;
}
