package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.EmployeeModel;

public interface IEmployeePersistencePort {
    EmployeeModel saveEmploy(EmployeeModel userModel);
    EmployeeModel findByIdUsuario(Long idEmployee);
}
