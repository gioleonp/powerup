package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CategoryModel {
    private int id;
    private String nombre;
    private String descripcion;
}
