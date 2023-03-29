package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import java.time.LocalDateTime;

public class OrderUseCaseDataTest {

    public static OrderModel getOrder() {
        OrderModel orderEntity =
                new OrderModel(
                        1L,
                        1L,
                        LocalDateTime.now(),
                        EOrderState.EN_PREPARACION,
                        2L,
                        null,
                        new RestaurantModel(
                                1L,
                                "Restaurante prueba",
                                "Direccion prueba",
                                1L,
                                "573004586742",
                                "http://url.com",
                                "1000000"));
        return orderEntity;
    }
}
