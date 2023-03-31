package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.EOrderState;import com.pragma.plazoleta.domain.model.OrderModel;import com.pragma.plazoleta.domain.model.RestaurantModel;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;import org.junit.jupiter.api.Order;import java.time.LocalDateTime;import java.util.Arrays;import java.util.List;public class OrderRepositoryDataTest {
    public static List<OrderEntity> getAllOrders() {
        OrderEntity orderEntity =
                new OrderEntity(
                        1L,
                        1L,
                        LocalDateTime.now(),
                        EOrderState.EN_PREPARACION,
                        2L,
                        new RestaurantEntity(
                                1L,
                                "Restaurante prueba",
                                "Direccion prueba",
                                1L,
                                "573004586742",
                                "http://url.com",
                                "1000000"));

        OrderEntity orderEntity2 =
                new OrderEntity(
                        2L,
                        1L,
                        LocalDateTime.now(),
                        EOrderState.PENDIENTE,
                        2L,
                        new RestaurantEntity(
                                1L,
                                "Restaurante prueba",
                                "Direccion prueba",
                                1L,
                                "573004586742",
                                "http://url.com",
                                "1000000"));
        return Arrays.asList(orderEntity, orderEntity2);

    }
}
