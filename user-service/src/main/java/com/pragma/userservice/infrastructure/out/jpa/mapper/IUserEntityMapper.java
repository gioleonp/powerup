package com.pragma.userservice.infrastructure.out.jpa.mapper;

import com.pragma.userservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.userservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    UserEntity toEntity(UserModel user);
    UserModel toUserModel(UserEntity objectEntity);
    List<UserModel> toUserModelList(List<UserEntity> userEntityList);
}