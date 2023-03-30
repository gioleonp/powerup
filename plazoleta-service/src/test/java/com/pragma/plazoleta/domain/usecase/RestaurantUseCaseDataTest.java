package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;import com.pragma.plazoleta.domain.model.RestaurantModel;import java.util.List;

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


    public static List<RestaurantResponseNameAndUrlDto> getRestaurantsNameAndUrlDtoList(){
        List<RestaurantResponseNameAndUrlDto> restaurantResponseNameAndUrlDtoList =
                List.of(
                        new RestaurantResponseNameAndUrlDto(
                                "Restaurant prueba",
                                "http://majo.com"
                        ),
                        new RestaurantResponseNameAndUrlDto(
                                "Donde gio",
                                "http://gio.com"
                        )
                );

        return restaurantResponseNameAndUrlDtoList;
    }
}
