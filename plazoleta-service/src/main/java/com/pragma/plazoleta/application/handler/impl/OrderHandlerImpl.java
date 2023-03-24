package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import com.pragma.plazoleta.application.mapper.IOrderDishRequestMapper;
import com.pragma.plazoleta.application.mapper.IOrderRequestMapper;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderHandlerImpl implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderDishRequestMapper orderDishRequestMapper;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto, List<OrderDishRequestDto> dishes) {

        orderServicePort.createOrder(
                orderRequestMapper.toModel(orderRequestDto),
                orderDishRequestMapper.toModelList(dishes));
    }
}
