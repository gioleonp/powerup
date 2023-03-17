package com.pragma.plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDto {
    private Long id;
    private String nombre;
    private String direccion;
    private long idPropietario;
    private String telefono;
    private String urlLogo;
    private String nit;
}
