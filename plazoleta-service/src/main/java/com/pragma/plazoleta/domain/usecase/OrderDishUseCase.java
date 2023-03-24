package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.exception.DishNotCorrespondToRestaurantException;import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IOrderDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;
    private final IDishServicePort dishServicePort;

    public OrderDishUseCase(
            IOrderDishPersistencePort orderDishPersistencePort, IDishServicePort dishServicePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
        this.dishServicePort = dishServicePort;
    }

    @Override
    public void createOrderDish(List<OrderDishModel> orderDishModelList, OrderModel orderModel) {

        for(OrderDishModel orderDishModel : orderDishModelList){

            DishModel dishModel = dishServicePort.findDishById(orderDishModel.getIdPlato());
            if(orderModel.getRestaurante().getId() != dishModel.getRestaurante().getId()) {
                throw new DishNotCorrespondToRestaurantException();
            }
            orderDishModel.setIdPlato(dishModel.getId());
        }


        orderDishPersistencePort.saveAll(orderDishModelList);
    }
}