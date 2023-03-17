package com.pragma.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishModel {
    private int id;
    private String nombre;
    private CategoryModel categoria;
    private String descripcion;
    private int precio;
    private RestaurantModel restaurante;
    private String urlImagen;
    private Boolean activo = true;
}
