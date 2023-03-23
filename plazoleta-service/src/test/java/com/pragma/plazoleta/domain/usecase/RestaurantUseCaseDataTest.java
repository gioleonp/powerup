package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.RestaurantModel;

public class RestaurantUseCaseDataTest {

    public static RestaurantModel getRestaurantModel() {
        RestaurantModel restaurantModel =
                new RestaurantModel(
                        1L,
                        "Restaurante prueba",
                        "Direccion prueba",
                        1L,
                        "573004586742",
                        "http://url.com",
                        "1000000");

        return restaurantModel;
    }
}
