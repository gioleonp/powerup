package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.domain.model.UserModel;

public interface IEmployeeHandler {
    void saveEmployee(UserModel user,Long idProprietary, Long idRestaurant);
}
