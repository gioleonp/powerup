package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;

public interface IOrderCodeServicePort {

    void saveOrderCode(OrderCodeModel orderCodeModel);

    OrderCodeModel getOrderCode(String orderCode);

    void deleteOrderCode(String orderCode);
    OrderCodeModel getOrderCodeByIdOrder(Long idOrder);
}
