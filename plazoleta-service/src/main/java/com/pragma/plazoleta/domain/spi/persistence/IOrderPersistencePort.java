package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderModel;
import java.util.List;

public interface IOrderPersistencePort {

    OrderModel createOrder(OrderModel order);

    int getNumberOfOrdersWithStateInPreparationPendingOrReady(Long idClient);

    List<OrderModel> findAllOrdersByStateAndRestaurant(
            EOrderState state, Long idRestaurant, int page, int size);

    OrderModel findById(Long id);

    OrderModel orderReady(OrderModel order);

    OrderModel deliverOrder(OrderModel order);

    OrderModel cancelOrder(OrderModel order);
}
