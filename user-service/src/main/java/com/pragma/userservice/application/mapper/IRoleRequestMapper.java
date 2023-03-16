package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.request.RoleRequestDto;
import com.pragma.userservice.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleRequestMapper {
    RoleModel toObject(RoleRequestDto roleRequestDto);
}
