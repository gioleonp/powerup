package com.pragma.usermicroservice.application.mapper;

import com.pragma.usermicroservice.application.dto.request.RoleRequestDto;
import com.pragma.usermicroservice.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleRequestMapper {
    RoleModel toObject(RoleRequestDto roleRequestDto);
}
