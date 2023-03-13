package com.pragma.usermicroservice.domain.spi;

import com.pragma.usermicroservice.domain.model.RoleModel;

import java.util.List;

public interface IRolePersistencePort {
    RoleModel saveRole(RoleModel roleModel);

    RoleModel findRole(Long id);
    List<RoleModel> getAllRoles();
}