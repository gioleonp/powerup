package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<OrderModel> findAllOrdersByStateAndRestaurant(
            EOrderState state, Long idRestaurant, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<OrderEntity> orderEntities =
                orderRepository
                        .findAllOrderByStateAndRestaurant(state.toString(), idRestaurant, pageable)
                        .toList();

        return orderEntityMapper.toModelList(orderEntities);
    }

    @Override
    public OrderModel findById(Long id) {

        Optional<OrderEntity> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new NoDataFoundException();
        }
        return orderEntityMapper.toModel(order.get());
    }

    @Override
    public OrderModel orderReady(OrderModel order) {
        return orderEntityMapper.toModel(orderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public OrderModel deliverOrder(OrderModel order) {
        return orderEntityMapper.toModel(orderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public OrderModel cancelOrder(OrderModel order) {
        return orderEntityMapper.toModel(orderRepository.save(orderEntityMapper.toEntity(order)));
    }
}
