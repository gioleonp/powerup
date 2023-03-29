package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.OrderCodeModel;

public interface IOrderCodePersistencePort {

    OrderCodeModel saveOrderCode(OrderCodeModel orderCodeEntity);

    OrderCodeModel getOrderCode(String orderCode);

    void deleteOrderCode(String orderCode);
    OrderCodeModel getOrderCodeByIdOrder(Long idOrderCode);
}
