package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishRequestPriceAndDescriptionDto {

    @NotNull(message = "PRECIO ES UN ATRIBUTO OBLIGATORIO")
    @Min(1)
    private int precio;

    @NotBlank(message = "DESCRIPCION ES UN ATRIBUTO OBLIGATORIO")
    private String descripcion;
}
