package com.pragma.plazoleta.application.dto.request;

import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDto {

    private Long id;

    @NotBlank(message = "NOMBRE ES UN ATRIBUTO OBLIGATORIO")
    private String nombre;

    @NotNull(message = "CATEGORIA ES UN ATRIBUTO OBLIGATORIO")
    private CategoryModel categoria;

    @NotBlank(message = "DESCRIPCION ES UN ATRIBUTO OBLIGATORIO")
    private String descripcion;

    @Range(min = 1, message = "PRECIO DEBE SER UN NUMERO ENTERO Y MAYOR A CERO")
    private int precio;

    @NotNull(message = "RESTAURANTE ES UN ATRIBUTO OBLIGATORIO")
    private RestaurantRequestDto restaurante;

    @NotBlank(message = "URL IMAGEN ES UN ATRIBUTO OBLIGATORIO")
    private String urlImagen;

    private Boolean activo;
}
