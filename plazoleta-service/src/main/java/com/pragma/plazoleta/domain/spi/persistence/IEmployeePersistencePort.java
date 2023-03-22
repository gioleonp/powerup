package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.UserModel;

public interface IEmployeePersistencePort {
    EmployeeModel saveEmploy(EmployeeModel userModel);
}
