package com.pragma.plazoleta.infrastructure.out.jpa.mapper;

import com.pragma.plazoleta.domain.model.EmployeeModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {

    EmployeeModel toModel(EmployeeEntity employeeEntity);
    EmployeeEntity toEntity(EmployeeModel employeeModel);
    List<EmployeeModel> toModelList(List<EmployeeEntity> employeeEntities);
    List<EmployeeEntity> toEntityList(List<EmployeeModel> employeeModels);

}
