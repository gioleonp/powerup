package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.EmployeeRequestDto;
import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRequestMapper {

    EmployeeModel toModel(EmployeeRequestDto employeeRequestDto);
}
