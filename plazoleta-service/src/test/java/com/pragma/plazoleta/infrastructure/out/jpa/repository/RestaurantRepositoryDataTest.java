package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import java.util.Arrays;
import java.util.List;

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

    public static List<RestaurantEntity> getListOfRestaurants() {
        RestaurantEntity restaurant =
                new RestaurantEntity(
                        1L,
                        "Comidas rapidas prueba",
                        "Direccion prueba",
                        1L,
                        "573004586743",
                        "http://url.com.es",
                        "1100000");

        RestaurantEntity restaurant2 =
                new RestaurantEntity(
                        2L,
                        "Avenida brazil",
                        "Direccion prueba",
                        1L,
                        "573004586742",
                        "http://url.com",
                        "1000000");

        return Arrays.asList(restaurant, restaurant2);
    }
}
