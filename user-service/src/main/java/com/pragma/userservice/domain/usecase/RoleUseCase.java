package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.spi.persistence.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(RoleModel roleModel) {
        rolePersistencePort.saveRole(roleModel);
    }

    @Override
    public RoleModel findRole(int id) {
        return rolePersistencePort.findRole(id);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }
}