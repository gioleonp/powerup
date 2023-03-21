package com.pragma.plazoleta.domain.model;

import com.pragma.plazoleta.application.dto.response.RoleResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private Long id;
    private String documentoDeIdentidad;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String contrasenia;
    private RoleResponseDto rol;
}
