package com.pragma.userservice.infrastructure.out.jpa.mapper;

import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRolEntityMapper {

    RoleEntity toEntity(RoleModel user);
    RoleModel toRoleModel(RoleEntity objectEntity);
    List<RoleModel> toRoleModelList(List<RoleEntity> userEntityList);
}