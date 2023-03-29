package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishRequestPriceAndDescriptionDto {

    @NotNull(message = "PRECIO ES UN ATRIBUTO OBLIGATORIO")
    @Range(min = 1, message = "PRECIO DEBE SER UN NUMERO ENTERO MAYOR A CERO")
    private int precio;

    @NotBlank(message = "DESCRIPCION ES UN ATRIBUTO OBLIGATORIO")
    private String descripcion;
}
