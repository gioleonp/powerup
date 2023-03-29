package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.request.OrderDishRequestDto;
import com.pragma.plazoleta.application.dto.request.OrderRequestDto;
import com.pragma.plazoleta.application.dto.response.OrderResponseDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import com.pragma.plazoleta.application.mapper.IOrderDishRequestMapper;
import com.pragma.plazoleta.application.mapper.IOrderRequestMapper;
import com.pragma.plazoleta.application.mapper.IOrderResponseMapper;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderModel;
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
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto, List<OrderDishRequestDto> dishes) {

        orderServicePort.createOrder(
                orderRequestMapper.toModel(orderRequestDto),
                orderDishRequestMapper.toModelList(dishes));
    }

    @Override
    public List<OrderResponseDto> findAllOrdersByStatusAndRestaurant(
            EOrderState state, Long idRestaurant, int page, int size) {
        List<OrderModel> orders =
                orderServicePort.findAllOrdersByStatusAndRestaurant(
                        state, idRestaurant, page, size);
        return orderResponseMapper.toResponseList(orders);
    }

    @Override
    public void assignOrder(Long idOrder, Long idRestaurant) {
        orderServicePort.assignOrder(idOrder, idRestaurant);
    }

    @Override
    public void orderReady(Long idOrder, Long idRestaurant) {
        orderServicePort.orderReady(idOrder, idRestaurant);
    }

    @Override
    public void deliverOrder(Long idOrder, Long idEmployee, String code) {
        orderServicePort.deliverOrder(idOrder, idEmployee, code);
    }
}
