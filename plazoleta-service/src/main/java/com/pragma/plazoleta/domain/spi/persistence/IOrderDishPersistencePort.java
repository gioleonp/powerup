package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.OrderDishModel;import java.util.List;

public interface IOrderDishPersistencePort {
    OrderDishModel createOrderDish(OrderDishModel orderDishModel);
    List<OrderDishModel> saveAll(List<OrderDishModel> orderDishModels);
    List<OrderDishModel> findAllByIdPedido(Long idPedido);
}
