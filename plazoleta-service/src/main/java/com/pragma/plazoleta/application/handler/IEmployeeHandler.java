package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.UserModel;

public interface IEmployeeHandler {
    void saveEmployee(UserRequestDto user,Long idProprietary, Long idRestaurant);
}
