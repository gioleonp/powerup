package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.NotClientToMakeAnOrderException;
import com.pragma.plazoleta.domain.exception.UserAlreadyHaveAnOrderPreparingPendingOrReadyException;import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import java.time.LocalDateTime;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IUserServiceCommunicationPort userServiceCommunicationPort;

    public OrderUseCase(
            IOrderPersistencePort orderPersistencePort,
            IOrderDishServicePort orderDishServicePort,
            IUserServiceCommunicationPort userServiceCommunicationPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderDishServicePort = orderDishServicePort;
        this.userServiceCommunicationPort = userServiceCommunicationPort;
    }

    @Override
    public void createOrder(OrderModel orderModel, List<OrderDishModel> orderDishModels) {
        // set default values
        orderModel.setFecha(LocalDateTime.now());
        orderModel.setEstado(EOrderState.PENDIENTE);

        // check if the user is a client
        UserModel userModel = userServiceCommunicationPort.findUserById(orderModel.getIdCliente());
        if (!userModel.getRol().getNombre().contains("CLIENTE")) {
            throw new NotClientToMakeAnOrderException();
        }

        // check if a user already has orders in pending, preparing or ready.
        int orderWithStatePendingPreparingOrReady =
                orderPersistencePort.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        userModel.getId());
        if (orderWithStatePendingPreparingOrReady > 0) {
            throw new UserAlreadyHaveAnOrderPreparingPendingOrReadyException();
        }

        // save order
        OrderModel order = orderPersistencePort.createOrder(orderModel);

        // set order id to each orderDish
        for (OrderDishModel orderDish : orderDishModels) {
            orderDish.setIdPedido(order.getId());
        }

        orderDishServicePort.createOrderDish(orderDishModels, orderModel);
    }
}
