package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.OrderDishRequestDto;import com.pragma.plazoleta.application.dto.request.OrderRequestDto;import java.util.List;

public interface IOrderHandler {

    void createOrder(OrderRequestDto orderRequestDto, List<OrderDishRequestDto> dishes);
}
