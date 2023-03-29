package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import java.util.List;

public interface IOrderServicePort {

    void createOrder(OrderModel orderModel, List<OrderDishModel> orderDishModelList);

    List<OrderModel> findAllOrdersByStatusAndRestaurant(
            EOrderState state, Long idEmployee, int page, int size);

    void assignOrder(Long idOrder, Long idEmployee);

    void orderReady(Long idOrder, Long idEmployee);

    void deliverOrder(Long idOrder, Long idEmployee, String code);
}
