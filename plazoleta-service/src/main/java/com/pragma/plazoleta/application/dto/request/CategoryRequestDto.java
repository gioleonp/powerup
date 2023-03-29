package com.pragma.plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {
    @NotBlank(message = "NOMBRE ES UN ATRIBUTO OBLIGATORIO")
    private String nombre;

    @NotBlank(message = "DESCRIPCION ES UN ATRIBUTO OBLIGATORIO")
    private String descripcion;
}
