package com.pragma.usermicroservice.domain.api;

import com.pragma.usermicroservice.domain.model.RoleModel;

import java.util.List;

public interface IRoleServicePort {

    void saveRole(RoleModel roleModel);

    RoleModel findRole(Long id);
    List<RoleModel> getAllRoles();
}