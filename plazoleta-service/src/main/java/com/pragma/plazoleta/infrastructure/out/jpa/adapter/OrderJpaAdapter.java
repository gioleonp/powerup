package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public OrderModel createOrder(OrderModel order) {
        OrderEntity orderEntity = orderRepository.save(orderEntityMapper.toEntity(order));

        return orderEntityMapper.toModel(orderEntity);
    }

    @Override
    public int getNumberOfOrdersWithStateInPreparationPendingOrReady(Long idClient) {
        return orderRepository.getNumberOfOrdersWithStateInPreparationPendingOrReady(idClient);
    }
}
