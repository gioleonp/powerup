package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.dto.response.OrderResponseDto;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderModel;
import java.util.List;

public interface IOrderHandler {

    void createOrder(OrderRequestDto orderRequestDto, List<OrderDishRequestDto> dishes);

    List<OrderResponseDto> findAllOrdersByStatusAndRestaurant(
            EOrderState state, Long idRestaurant, int page, int size);

    void assignOrder(Long idOrder, Long idRestaurant);

    void orderReady(Long idOrder, Long idRestaurant);

    void deliverOrder(Long idOrder, Long idEmployee, String code);

    void cancelOrder(Long idOrder, Long idClient);
}
