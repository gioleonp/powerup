package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.handler.IEmployeeHandler;
import com.pragma.plazoleta.application.mapper.IEmployeeRequestMapper;
import com.pragma.plazoleta.application.mapper.IUserRequestMapper;
import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.model.UserModel;import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class EmployeeHandlerImpl implements IEmployeeHandler {

    private final IEmployeeServicePort employeeServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveEmployee(UserModel user, Long idProprietary, Long idRestaurant) {
        employeeServicePort
                .saveEmployee(userRequestMapper.toModel(user), idProprietary, idRestaurant);
    }
}
