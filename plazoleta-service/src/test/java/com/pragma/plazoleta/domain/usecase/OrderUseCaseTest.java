package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IOrderCodeServicePort;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.exception.ClientIsNotOrderOwnerException;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.EmployeeIsNotOrderChefException;
import com.pragma.plazoleta.domain.exception.EmployeeNotBelongToTheRestaurantException;
import com.pragma.plazoleta.domain.exception.NotClientToMakeAnOrderException;
import com.pragma.plazoleta.domain.exception.OrderCodeDoNotMatchException;
import com.pragma.plazoleta.domain.exception.UserAlreadyHaveAnOrderPreparingPendingOrReadyException;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.OrderCodeModel;
import com.pragma.plazoleta.domain.model.OrderDishModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.RolModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.ITwilioServiceCommunicationPort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;
import org.h2.schema.Domain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderUseCaseTest {

    @InjectMocks OrderUseCase underTest;

    @Mock IOrderPersistencePort orderPersistencePort;
    @Mock IOrderDishServicePort orderDishServicePort;
    @Mock IUserServiceCommunicationPort userServiceCommunicationPort;
    @Mock IEmployeeServicePort employeeServicePort;
    @Mock ITwilioServiceCommunicationPort twilioServiceCommunicationPort;
    @Mock IOrderCodeServicePort orderCodeServicePort;

    @Test
    void createOrderOk() {
        // Given
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(1);
        OrderDishModel expectedOrderDish = OrderDishDataTest.getOrderDish();

        expectedClient.setRol(new RolModel(1, "ROLE_CLIENTE", ""));

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(orderPersistencePort.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        expectedClient.getId()))
                .thenReturn(0);
        when(orderPersistencePort.findAllOrders()).thenReturn(Arrays.asList(expectedOrder));
        when(orderPersistencePort.createOrder(expectedOrder)).thenReturn(expectedOrder);

        underTest.createOrder(expectedOrder, Arrays.asList(expectedOrderDish));

        // Then
        verify(orderPersistencePort).createOrder(expectedOrder);
        verify(orderDishServicePort)
                .createOrderDish(Arrays.asList(expectedOrderDish), expectedOrder);
    }

    @Test
    void createOrderIsNotAClient() {
        // Given
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(1);
        OrderDishModel expectedOrderDish = OrderDishDataTest.getOrderDish();

        expectedClient.setRol(new RolModel(1, "ROLE_EMPLEADO", ""));

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(orderPersistencePort.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        expectedClient.getId()))
                .thenReturn(0);
        when(orderPersistencePort.findAllOrders()).thenReturn(Arrays.asList(expectedOrder));

        // Then
        assertThatExceptionOfType(NotClientToMakeAnOrderException.class)
                .isThrownBy(
                        () ->
                                underTest.createOrder(
                                        expectedOrder, Arrays.asList(expectedOrderDish)));
    }

    @Test
    void createOrderClientAlreadyHaveAnOrder() {
        // Given
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(1);
        OrderDishModel expectedOrderDish = OrderDishDataTest.getOrderDish();

        expectedClient.setRol(new RolModel(1, "ROLE_CLIENTE", ""));

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(orderPersistencePort.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        expectedClient.getId()))
                .thenReturn(1);
        when(orderPersistencePort.findAllOrders()).thenReturn(Arrays.asList(expectedOrder));

        // Then
        assertThatExceptionOfType(UserAlreadyHaveAnOrderPreparingPendingOrReadyException.class)
                .isThrownBy(
                        () ->
                                underTest.createOrder(
                                        expectedOrder, Arrays.asList(expectedOrderDish)));
    }

    @Test
    void assignOrderOk() {
        EmployeeModel employeeOrder = new EmployeeModel(1L, 1L);

        OrderModel order = OrderUseCaseDataTest.getAllOrders().get(1);
        order.setIdChef(null); // reset the default idChef

        // When
        when(orderPersistencePort.findById(order.getId())).thenReturn(order);
        when(employeeServicePort.findByIdUsuario(employeeOrder.getIdUsuario()))
                .thenReturn(employeeOrder);
        underTest.assignOrder(order.getId(), employeeOrder.getIdUsuario());

        // Then
        verify(employeeServicePort).findByIdUsuario(employeeOrder.getIdUsuario());
        verify(orderPersistencePort).findById(order.getId());
        verify(orderPersistencePort).createOrder(order);
    }

    @Test
    void assignOrderEmployeeNotBelongsToRestaurant() {
        EmployeeModel employeeOrder = new EmployeeModel(1L, 2L);

        OrderModel order = OrderUseCaseDataTest.getAllOrders().get(1);
        order.setIdChef(null); // reset the default idChef

        // When
        when(orderPersistencePort.findById(order.getId())).thenReturn(order);
        when(employeeServicePort.findByIdUsuario(employeeOrder.getIdUsuario()))
                .thenReturn(employeeOrder);

        // Then
        assertThatExceptionOfType(EmployeeNotBelongToTheRestaurantException.class)
                .isThrownBy(
                        () -> underTest.assignOrder(order.getId(), employeeOrder.getIdUsuario()));
    }

    @Test
    void assignOrderOrderIsNotPending() {
        EmployeeModel employeeOrder = new EmployeeModel(1L, 1L);

        OrderModel order = OrderUseCaseDataTest.getAllOrders().get(0);
        order.setIdChef(null); // reset the default idChef

        // When
        when(orderPersistencePort.findById(order.getId())).thenReturn(order);
        when(employeeServicePort.findByIdUsuario(employeeOrder.getIdUsuario()))
                .thenReturn(employeeOrder);

        // Then
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(
                        () -> underTest.assignOrder(order.getId(), employeeOrder.getIdUsuario()));
    }

    @Test
    void orderReadyOk() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        underTest.orderReady(expectedOrder.getId(), expectedEmployee.getIdUsuario());

        // Then
        verify(orderPersistencePort).orderReady(expectedOrder);
        verify(employeeServicePort).findByIdUsuario(expectedEmployee.getIdUsuario());
        verify(orderPersistencePort).findById(expectedOrder.getId());
    }

    @Test
    void orderReadyWrongChef() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(3L, 1L);
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        // Then
        assertThatExceptionOfType(EmployeeIsNotOrderChefException.class)
                .isThrownBy(
                        () ->
                                underTest.orderReady(
                                        expectedOrder.getId(), expectedEmployee.getIdUsuario()));
    }

    @Test
    void orderReadyWrongState() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        expectedOrder.setEstado(EOrderState.PENDIENTE);

        // When
        when(userServiceCommunicationPort.findUserById(expectedClient.getId()))
                .thenReturn(expectedClient);
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        // Then
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(
                        () ->
                                underTest.orderReady(
                                        expectedOrder.getId(), expectedEmployee.getIdUsuario()));
    }

    @Test
    void deliverOrderOk() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        OrderCodeModel expectedOrderCode = new OrderCodeModel("123456", 1L);

        expectedOrder.setEstado(EOrderState.LISTO);

        // When
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);
        when(orderCodeServicePort.getOrderCodeByIdOrder(expectedOrderCode.getIdOrder()))
                .thenReturn(expectedOrderCode);

        underTest.deliverOrder(
                expectedOrder.getId(),
                expectedEmployee.getIdUsuario(),
                expectedOrderCode.getCode());

        // Then
        verify(orderPersistencePort).findById(expectedOrder.getId());
        verify(orderCodeServicePort).getOrderCodeByIdOrder(expectedOrderCode.getIdOrder());
        verify(orderPersistencePort).deliverOrder(expectedOrder);
        verify(employeeServicePort).findByIdUsuario(expectedEmployee.getIdUsuario());
    }

    @Test
    void deliverOrderEmployeeNotBelongsToRestaurant() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 3L);
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        OrderCodeModel expectedOrderCode = new OrderCodeModel("123456", 1L);

        expectedOrder.setEstado(EOrderState.LISTO);

        // When
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);
        when(orderCodeServicePort.getOrderCodeByIdOrder(expectedOrderCode.getIdOrder()))
                .thenReturn(expectedOrderCode);

        // Then
        assertThatExceptionOfType(EmployeeNotBelongToTheRestaurantException.class)
                .isThrownBy(
                        () ->
                                underTest.deliverOrder(
                                        expectedOrder.getId(),
                                        expectedEmployee.getIdUsuario(),
                                        expectedOrderCode.getCode()));
    }

    @Test
    void deliverOrderOrderIsNotInReadyState() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        OrderCodeModel expectedOrderCode = new OrderCodeModel("123456", 1L);

        expectedOrder.setEstado(EOrderState.PENDIENTE);

        // When
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);
        when(orderCodeServicePort.getOrderCodeByIdOrder(expectedOrderCode.getIdOrder()))
                .thenReturn(expectedOrderCode);

        // Then
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(
                        () ->
                                underTest.deliverOrder(
                                        expectedOrder.getId(),
                                        expectedEmployee.getIdUsuario(),
                                        expectedOrderCode.getCode()));
    }

    @Test
    void deliverOrderCodeNotMatch() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        OrderCodeModel expectedOrderCode = new OrderCodeModel("123456", 1L);
        String wrongCode = "654321";

        expectedOrder.setEstado(EOrderState.LISTO);

        // When
        when(employeeServicePort.findByIdUsuario(expectedEmployee.getIdUsuario()))
                .thenReturn(expectedEmployee);
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);
        when(orderCodeServicePort.getOrderCodeByIdOrder(expectedOrderCode.getIdOrder()))
                .thenReturn(expectedOrderCode);

        // Then
        assertThatExceptionOfType(OrderCodeDoNotMatchException.class)
                .isThrownBy(
                        () ->
                                underTest.deliverOrder(
                                        expectedOrder.getId(),
                                        expectedEmployee.getIdUsuario(),
                                        wrongCode));
    }

    @Test
    void cancelOrderOk() {
        // Given
        Long idClient = 1L;
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        expectedOrder.setEstado(EOrderState.PENDIENTE);

        // When
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        underTest.cancelOrder(expectedOrder.getId(), idClient);

        // Then
        verify(orderPersistencePort).findById(expectedOrder.getId());
        verify(orderPersistencePort).cancelOrder(expectedOrder);
    }

    @Test
    void cancelOrderWrongClient() {
        // Given
        Long idClient = 2L;
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        expectedOrder.setEstado(EOrderState.PENDIENTE);

        // When
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        // Then
        assertThatExceptionOfType(ClientIsNotOrderOwnerException.class)
                .isThrownBy(() -> underTest.cancelOrder(expectedOrder.getId(), idClient));
    }

    @Test
    void cancelOrderStateIsNotPending() {
        // Given
        Long idClient = 1L;
        OrderModel expectedOrder = OrderUseCaseDataTest.getAllOrders().get(0);
        expectedOrder.setEstado(EOrderState.LISTO);

        // When
        when(orderPersistencePort.findById(expectedOrder.getId())).thenReturn(expectedOrder);

        // Then
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> underTest.cancelOrder(expectedOrder.getId(), idClient));
    }
}
