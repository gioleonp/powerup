package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.CategoryModel;import com.pragma.plazoleta.domain.model.DishModel;import com.pragma.plazoleta.domain.model.RestaurantModel;public class DishUseCaseDataTest {
    public static DishModel getDishModel(){
        DishModel expectedDishModel =
                new DishModel(
                        1L,
                        "macarrones",
                        new CategoryModel(1, "pastas", ""),
                        "macarrones con queso",
                        20,
                        null,
                        "imagen.url",
                        true);

        RestaurantModel expectedRestaurant =
                new RestaurantModel(
                        1L,
                        "donde keylly",
                        "piedra de bolivar",
                        1L,
                        "573058388527",
                        "http.foto.com",
                        "1000000");

        expectedDishModel.setRestaurante(expectedRestaurant);

        return expectedDishModel;

    }
}
