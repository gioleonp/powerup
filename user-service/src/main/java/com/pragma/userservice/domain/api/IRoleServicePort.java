package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.RoleModel;

import java.util.List;

public interface IRoleServicePort {

    void saveRole(RoleModel roleModel);

    RoleModel findRole(int id);
    List<RoleModel> getAllRoles();
}