package com.pragma.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantModel {
    private Long id;
    private String nombre;
    private String direccion;
    private long idPropietario;
    private String telefono;
    private String urlLogo;
    private String nit;
}
