package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;import javax.validation.constraints.NotBlank;import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "DOCUMENTO DE IDENTIDAD ES UN ATRIBUTO OBLIGATORIO")
    @Pattern(regexp = "\\d{10}", message = "DOCUMENTO DE IDENTIDAD INVALIDO")
    private String documentoDeIdentidad;

    @NotBlank(message = "NOMBRE ES UN ATRIBUTO OBLIGATORIO")
    private String nombre;
    @NotBlank(message = "APELLIDO ES UN ATRIBUTO OBLIGATORIO")
    private String apellido;

    @NotBlank(message = "CELULAR ES UN ATRIBUTO OBLIGATORIO")
    @Pattern(regexp = "\\+?573\\d{9}", message = "CELULAR NO VALIDO")
    private String celular;

    @NotBlank(message = "EMAIL ES UN ATRIBUTO OBLIGATORIO")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "EMAIL INVALIDO")
    private String email;

    @NotBlank(message = "CONTRASENIA ES UN ATRIBUTO OBLIGATORIO")
    private String contrasenia;
}
