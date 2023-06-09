package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IOrderCodeServicePort;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.exception.ClientIsNotOrderOwnerException;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.EmployeeIsNotOrderChefException;
import com.pragma.plazoleta.domain.exception.EmployeeNotBelongToTheRestaurantException;
import com.pragma.plazoleta.domain.exception.NotClientToMakeAnOrderException;
import com.pragma.plazoleta.domain.exception.OrderCodeDoNotMatchException;
import com.pragma.plazoleta.domain.exception.UserAlreadyHaveAnOrderPreparingPendingOrReadyException;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.MessageModel;
import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.ITwilioServiceCommunicationPort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import java.time.LocalDateTime;
import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IOrderDishServicePort orderDishServicePort;
    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IEmployeeServicePort employeeServicePort;
    private final ITwilioServiceCommunicationPort twilioServiceCommunicationPort;
    private final IOrderCodeServicePort orderCodeServicePort;

    public OrderUseCase(
            IOrderPersistencePort orderPersistencePort,
            IOrderDishServicePort orderDishServicePort,
            IUserServiceCommunicationPort userServiceCommunicationPort,
            IEmployeeServicePort employeeServicePort,
            ITwilioServiceCommunicationPort twilioServiceCommunicationPort,
            IOrderCodeServicePort orderCodeServicePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderDishServicePort = orderDishServicePort;
        this.userServiceCommunicationPort = userServiceCommunicationPort;
        this.employeeServicePort = employeeServicePort;
        this.twilioServiceCommunicationPort = twilioServiceCommunicationPort;
        this.orderCodeServicePort = orderCodeServicePort;
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
        OrderModel foundOrder = orderPersistencePort.createOrder(orderModel);

        // Verify people is not ordering a dish with quantity lower than 1
        for (OrderDishModel orderDish : orderDishModels) {
            if (orderDish.getCantidad() <= 0) {
                orderPersistencePort.deleteOrder(foundOrder.getId());

                throw new DomainException(
                        "LA CANTIDAD DE PLATOS DEBE SER UN NUMERO ENTERO MAYOR A CERO");
            }
            orderDish.setIdPedido(foundOrder.getId());
        }

        orderDishServicePort.createOrderDish(orderDishModels, foundOrder);
    }

    @Override
    public List<OrderModel> findAllOrdersByStatusAndRestaurant(
            EOrderState state, Long idEmployee, int page, int size) {

        // Get employee to obtain the restaurant where he is working for
        EmployeeModel employeeModel = employeeServicePort.findByIdUsuario(idEmployee);

        // Find orders
        List<OrderModel> orderModels =
                orderPersistencePort.findAllOrdersByStateAndRestaurant(
                        state, employeeModel.getIdRestaurante(), page, size);

        // Set the orderDishes asked in the order
        for (OrderModel orderModel : orderModels) {
            List<OrderDishModel> orderDishModels =
                    orderDishServicePort.findAllByIdPedido(orderModel.getId());
            orderModel.setOrderDishes(orderDishModels);
        }

        return orderModels;
    }

    @Override
    public void deleteOrder(Long idOrder) {
        orderPersistencePort.deleteOrder(idOrder);
    }

    @Override
    public void assignOrder(Long idOrder, Long idEmployee) {

        OrderModel order = orderPersistencePort.findById(idOrder);
        EmployeeModel employeeModel = employeeServicePort.findByIdUsuario(idEmployee);

        if (!order.getRestaurante().getId().equals(employeeModel.getIdRestaurante())) {
            throw new EmployeeNotBelongToTheRestaurantException();
        } else if (order.getEstado() != EOrderState.PENDIENTE) {
            throw new DomainException("ONLY A PENDING ORDER CAN BE TAKEN BY A EMPLOYEE");
        }

        order.setIdChef(employeeModel.getIdUsuario()); // assign employee
        order.setEstado(EOrderState.EN_PREPARACION); // change state

        // updating the order.
        orderPersistencePort.createOrder(order);
    }

    @Override
    public void orderReady(Long idOrder, Long idEmployee) {

        // Get order and employee
        OrderModel order = orderPersistencePort.findById(idOrder);
        EmployeeModel employeeModel = employeeServicePort.findByIdUsuario(idEmployee);

        if (!order.getIdChef().equals(employeeModel.getIdUsuario())) {
            throw new EmployeeIsNotOrderChefException();
        } else if (order.getEstado() != EOrderState.EN_PREPARACION) {
            throw new DomainException("ONLY AN ORDER IN PREPARATION CAN BE PLACED BY AN EMPLOYEE");
        }

        // Change state
        order.setEstado(EOrderState.LISTO);

        // Generate code
        OrderCodeModel orderCode = new OrderCodeModel();
        orderCode.setCode(OrderCodeModel.generateCode());
        orderCode.setIdOrder(order.getId());

        // Save code
        orderCodeServicePort.saveOrderCode(orderCode);

        // Find user
        UserModel client = userServiceCommunicationPort.findUserById(order.getIdCliente());

        // Message
        String messageCode =
                "Hola, "
                        + client.getNombre()
                        + ". Tu pedido ya esta listo, para reclamarlo proporciona el siguiente codigo: \n"
                        + orderCode.getCode();

        MessageModel message = new MessageModel(client.getCelular(), messageCode);

        // send message
        twilioServiceCommunicationPort.sendMessage(message);

        orderPersistencePort.orderReady(order);
    }

    @Override
    public void deliverOrder(Long idOrder, Long idEmployee, String code) {

        // Find order and employee
        OrderModel order = orderPersistencePort.findById(idOrder);
        EmployeeModel employee = employeeServicePort.findByIdUsuario(idEmployee);

        // Validate them
        if (order.getEstado() != EOrderState.LISTO) {
            throw new DomainException("ONLY A READY ORDER CAN BE DELIVER");
        } else if (!order.getRestaurante().getId().equals(employee.getIdRestaurante())) {
            throw new EmployeeNotBelongToTheRestaurantException();
        }

        // Find orderCode to match it with the given one.
        OrderCodeModel orderCode = orderCodeServicePort.getOrderCodeByIdOrder(order.getId());
        if (!orderCode.getCode().equals(code)) {
            throw new OrderCodeDoNotMatchException();
        }

        // Changing state to delivered
        order.setEstado(EOrderState.ENTREGADO);
        // Delete order code to avoid repeated codes errors
        orderCodeServicePort.deleteOrderCode(code);

        // Save order with changed state.
        orderPersistencePort.deliverOrder(order);
    }

    @Override
    public void cancelOrder(Long idOrder, Long idClient) {

        OrderModel order = orderPersistencePort.findById(idOrder);

        if (!order.getIdCliente().equals(idClient)) {
            throw new ClientIsNotOrderOwnerException();
        } else if (order.getEstado() != EOrderState.PENDIENTE) {
            throw new DomainException(
                    "Lo sentimos, tu pedido esta en preparacion y ya no puede cancelarse");
        }

        order.setEstado(EOrderState.CANCELADO);

        orderPersistencePort.cancelOrder(order);
    }
}
