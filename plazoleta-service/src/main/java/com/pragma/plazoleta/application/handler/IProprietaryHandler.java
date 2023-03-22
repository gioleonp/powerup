package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;

public interface IProprietaryHandler {

    void saveEmployee(UserRequestDto userRequestDto, Long idProprietary, Long idRestaurant);

}
