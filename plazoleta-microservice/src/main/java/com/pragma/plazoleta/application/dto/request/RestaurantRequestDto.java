package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {
    private String nombre;
    private String direccion;
    private long idPropietario;
    private String telefono;
    private String urlLogo;
    private String nit;
}
