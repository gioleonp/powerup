package com.pragma.usermicroservice.domain.usecase;

import com.pragma.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.usermicroservice.domain.model.RoleModel;
import com.pragma.usermicroservice.domain.spi.IRolePersistencePort;

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
    public RoleModel findRole(Long id) {
        return rolePersistencePort.findRole(id);
    }

    @Override
    public List<RoleModel> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }
}