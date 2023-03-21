package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String documentoDeIdentidad;
    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String contrasenia;
}
