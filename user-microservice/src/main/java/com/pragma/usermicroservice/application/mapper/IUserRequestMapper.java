package com.pragma.usermicroservice.application.mapper;

import com.pragma.usermicroservice.application.dto.request.UserRequestDto;
import com.pragma.usermicroservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    UserModel toUser(UserRequestDto userRequestDto);
}
