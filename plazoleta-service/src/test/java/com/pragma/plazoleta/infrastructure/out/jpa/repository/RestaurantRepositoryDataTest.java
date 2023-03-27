package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;

public class RestaurantRepositoryDataTest {

    public static RestaurantEntity getRestaurant() {
        RestaurantEntity restaurant =
                new RestaurantEntity(
                        1L,
                        "Restaurante prueba",
                        "Direccion prueba",
                        1L,
                        "573004586742",
                        "http://url.com",
                        "1000000");

        return restaurant;
    }
}
