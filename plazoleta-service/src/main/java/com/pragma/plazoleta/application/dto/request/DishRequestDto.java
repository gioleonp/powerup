package com.pragma.plazoleta.application.dto.request;

import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDto {
    private String nombre;
    private CategoryModel categoria;
    private String descripcion;
    private int precio;
    private RestaurantModel restaurante;
    private String urlImagen;
    private Boolean activo;
}
