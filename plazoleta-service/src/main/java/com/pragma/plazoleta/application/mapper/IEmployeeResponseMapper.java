package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.EmployeeResponseDto;
import com.pragma.plazoleta.domain.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeResponseMapper {
    EmployeeResponseDto toResponse(EmployeeModel employeeModel);
    List<EmployeeResponseDto> toResponseList(List<EmployeeModel> employeeModels);

}
