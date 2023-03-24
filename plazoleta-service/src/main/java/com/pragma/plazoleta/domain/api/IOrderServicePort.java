package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.OrderDishModel;import com.pragma.plazoleta.domain.model.OrderModel;import java.util.List;

public interface IOrderServicePort {

    void createOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList);
}
