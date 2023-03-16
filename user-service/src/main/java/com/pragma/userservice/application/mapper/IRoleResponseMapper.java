package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.response.RoleResponseDto;
import com.pragma.userservice.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    RoleResponseDto toResponse(RoleModel objectModel);

    List<RoleResponseDto> toResponseList(List<RoleModel> objectModelList);
}
