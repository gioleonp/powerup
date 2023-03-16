package com.pragma.userservice.domain.spi.persistence;

import com.pragma.userservice.domain.model.RoleModel;

import java.util.List;

public interface IRolePersistencePort {
    RoleModel saveRole(RoleModel roleModel);

    RoleModel findRole(int id);
    List<RoleModel> getAllRoles();
}