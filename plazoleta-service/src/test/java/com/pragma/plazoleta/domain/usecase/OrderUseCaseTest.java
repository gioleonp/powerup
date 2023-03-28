package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IOrderCodeServicePort;
import com.pragma.plazoleta.domain.api.IOrderDishServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.EmployeeIsNotOrderChefException;
import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.RolModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.ITwilioServiceCommunicationPort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
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
    void orderReadyOk() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getOrder();

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
        OrderModel expectedOrder = OrderUseCaseDataTest.getOrder();

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
                                        expectedOrder.getId(), expectedEmployee.getIdUsuario()))
                .withMessageMatching("EMPLOYEE IS NOT THE CHEF OF THE ORDER");
    }

    @Test
    void orderReadyWrongState() {
        // Given
        EmployeeModel expectedEmployee = new EmployeeModel(2L, 1L);
        UserModel expectedClient = UserDataTest.getUserModel();
        OrderModel expectedOrder = OrderUseCaseDataTest.getOrder();
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
}
