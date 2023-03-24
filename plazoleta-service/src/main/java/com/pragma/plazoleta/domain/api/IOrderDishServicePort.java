package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import java.util.List;

public interface IOrderDishServicePort {

    void createOrderDish(List<OrderDishModel> orderDishModels, OrderModel orderModel);
}
