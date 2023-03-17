package com.pragma.plazoleta.application.dto.response;

import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DishResponseDto {
    private int id;
    private String nombre;
    private CategoryModel categoria;
    private String descripcion;
    private int precio;
    private RestaurantModel restaurante;
    private String urlImagen;
    private Boolean activo;
}
