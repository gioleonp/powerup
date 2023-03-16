package com.pragma.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {
    private long id;
    private String nombre;
    private String direccion;
    private long idPropietario;
    private String telefono;
    private String urlLogo;
    private String nit;
}
