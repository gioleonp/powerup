package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.application.dto.response.UserResponseWithPasswordDto;
import com.pragma.plazoleta.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    UserModel toModel(UserRequestDto userRequestDto);

}
