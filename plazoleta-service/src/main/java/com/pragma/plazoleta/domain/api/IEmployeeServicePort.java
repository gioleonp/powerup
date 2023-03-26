package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.UserModel;

public interface IEmployeeServicePort {
    void saveEmployee(UserModel userModel, Long idProprietary, Long idRestaurant);
    EmployeeModel findByIdUsuario(Long idEmployee);
}
