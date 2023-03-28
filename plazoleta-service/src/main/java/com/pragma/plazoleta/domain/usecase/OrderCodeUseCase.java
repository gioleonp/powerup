package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IOrderCodeServicePort;
import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderCodePersistencePort;import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;

public class OrderCodeUseCase implements IOrderCodeServicePort {

    private final IOrderCodePersistencePort orderCodePersistencePort;

    public OrderCodeUseCase(IOrderCodePersistencePort orderCodePersistencePort) {
        this.orderCodePersistencePort = orderCodePersistencePort;
    }

    @Override
    public void saveOrderCode(OrderCodeModel orderCodeModel) {
       orderCodePersistencePort.saveOrderCode(orderCodeModel);
    }

    @Override
    public OrderCodeModel getOrderCode(String orderCode) {
        return orderCodePersistencePort.getOrderCode(orderCode);
    }

    @Override
    public void deleteOrderCode(String orderCode) {
        orderCodePersistencePort.deleteOrderCode(orderCode);
    }
}
