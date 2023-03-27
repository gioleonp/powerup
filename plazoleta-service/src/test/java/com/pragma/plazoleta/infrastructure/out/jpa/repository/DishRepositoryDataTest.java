package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import java.util.Arrays;
import java.util.List;

public class DishRepositoryDataTest {

    public static List<DishEntity> getDishes() {
        RestaurantEntity restaurant =
                new RestaurantEntity(
                        1L,
                        "Restaurante prueba",
                        "Direccion prueba",
                        1L,
                        "573004586742",
                        "http://url.com",
                        "1000000");

        DishEntity dishEntity =
                new DishEntity(
                        1L,
                        "Arroz de pollo",
                        new CategoryEntity(1, "Granos", "Arroz, lentejas, frijoles"),
                        "Arroz de pollo con salchichas",
                        20,
                        restaurant,
                        "arroz",
                        true);
        DishEntity dishEntity2 =
                new DishEntity(
                        2L,
                        "Jugo de corozo",
                        new CategoryEntity(2, "Jugos", "Jugos naturales"),
                        "Jugo de corozo natural",
                        10,
                        restaurant,
                        "jugo.url",
                        true);

        List<DishEntity> dishes = Arrays.asList(dishEntity, dishEntity2);

        return dishes;
    }
}
