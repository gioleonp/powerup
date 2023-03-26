package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;

public class EmployeeUseCase implements IEmployeeServicePort {

    private IEmployeePersistencePort employeePersistencePort;
    private IUserServiceCommunicationPort userServiceCommunicationPort;
    private IRestaurantServicePort restaurantServicePort;

    public EmployeeUseCase(
            IEmployeePersistencePort employeePersistencePort,
            IUserServiceCommunicationPort userServiceCommunicationPort,
            IRestaurantServicePort restaurantServicePort) {
        this.employeePersistencePort = employeePersistencePort;
        this.userServiceCommunicationPort = userServiceCommunicationPort;
        this.restaurantServicePort = restaurantServicePort;
    }

    @Override
    public void saveEmployee(UserModel userModel, Long idProprietary, Long idRestaurant) {

        UserModel foundUser = userServiceCommunicationPort.findByEmail(userModel.getEmail());
        RestaurantModel restaurant = restaurantServicePort.findRestaurantById(idRestaurant);

        if (idProprietary != restaurant.getIdPropietario()) {
            throw new DomainException("EL PROPIETARIO NO ES DUEÑO DEL RESTAURANTE");
        }

        EmployeeModel employeeModel = new EmployeeModel(foundUser.getId(), idRestaurant);

        employeePersistencePort.saveEmploy(employeeModel);
    }

    @Override
    public EmployeeModel findByIdUsuario(Long idEmployee) {
        return employeePersistencePort.findByIdUsuario(idEmployee);
    }
}
