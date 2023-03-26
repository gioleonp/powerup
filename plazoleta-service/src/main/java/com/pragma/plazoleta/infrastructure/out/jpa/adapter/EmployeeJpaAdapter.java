package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IEmployeePersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Override
    public EmployeeModel saveEmploy(EmployeeModel employeeModel) {
        EmployeeEntity employeeEntity = employeeEntityMapper.toEntity(employeeModel);
        return employeeEntityMapper.toModel(employeeRepository.save(employeeEntity));
    }

    @Override
    public EmployeeModel findByIdUsuario(Long idEmployee) {

        EmployeeEntity employeeEntity =
                employeeRepository.findByIdUsuario(idEmployee);
        if (employeeEntity == null) {
            throw new NoDataFoundException();
        }

        return employeeEntityMapper.toModel(employeeEntity);
    }
}
