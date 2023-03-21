package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.application.dto.response.UserResponseWithPasswordDto;
import com.pragma.plazoleta.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    UserModel toModel(UserResponseDto userResponseDto);
    UserModel toModelWithPassword(UserResponseWithPasswordDto userResponseWithPasswordDto);

    List<UserResponseDto> toResponseList(List<UserModel> userModels);
    List<UserModel> toModelList(List<UserResponseDto> userResponseList);
}
