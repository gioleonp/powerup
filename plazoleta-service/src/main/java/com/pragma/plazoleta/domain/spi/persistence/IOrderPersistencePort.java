package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.OrderModel;

public interface IOrderPersistencePort {

    OrderModel createOrder(OrderModel order);
}
