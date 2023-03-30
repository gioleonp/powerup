package com.pragma.userservice.infrastructure.out.jpa.adapter;

import com.pragma.userservice.infrastructure.exception.NoDataFoundException;
import com.pragma.userservice.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.spi.persistence.IRolePersistencePort;
import com.pragma.userservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.userservice.infrastructure.out.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;


    @Override
    public RoleModel saveRole(RoleModel rolModel) {
        RoleEntity rolEntity = rolRepository.save(rolEntityMapper.toEntity(rolModel));
        return rolEntityMapper.toRoleModel(rolEntity);
    }

    @Override
    public RoleModel findRole(int id) {

        Optional<RoleEntity> rol = rolRepository.findById(id);
        if (rol.isEmpty()){
            throw new NoDataFoundException("ROL");
        }
        return rolEntityMapper.toRoleModel(rol.get());
    }

    @Override
    public List<RoleModel> getAllRoles() {
        List<RoleEntity> entityList = rolRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException("ROLES");
        }
        return rolEntityMapper.toRoleModelList(entityList);
    }
}