package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.response.UserResponseDto;
import com.pragma.userservice.application.dto.response.UserResponseDtoWithPassword;
import com.pragma.userservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(UserModel userModel);
    UserResponseDtoWithPassword toResponseWithPassword(UserModel userModel);

    List<UserResponseDto> toResponseList(List<UserModel> userModelList);
}
