package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.OrderDishModel;

public class OrderDishDataTest {

    public static OrderDishModel getOrderDish() {
        OrderDishModel orderDishModel = new OrderDishModel(1L, 1l, 1);

        return orderDishModel;
    }
}
