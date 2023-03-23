package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantNameAndUrlEntity {
    private String nombre;
    private String url;
}
