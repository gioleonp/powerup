package com.pragma.userservice.application.handler.impl;

import com.pragma.userservice.application.dto.request.RoleRequestDto;
import com.pragma.userservice.application.dto.response.RoleResponseDto;
import com.pragma.userservice.application.handler.IRoleHandler;
import com.pragma.userservice.application.mapper.IRoleRequestMapper;
import com.pragma.userservice.application.mapper.IRoleResponseMapper;
import com.pragma.userservice.domain.api.IRoleServicePort;
import com.pragma.userservice.domain.model.RoleModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleHandler implements IRoleHandler {

    private final IRoleServicePort roleServicePort;
    private final IRoleRequestMapper roleRequestMapper;
    private final IRoleResponseMapper roleResponseMapper;

    @Override
    public void saveRole(RoleRequestDto roleRequestDto) {
        RoleModel roleModel = roleRequestMapper.toObject(roleRequestDto);
        roleServicePort.saveRole(roleModel);
    }

    @Override
    public RoleResponseDto findRole(int id) {
        return roleResponseMapper.toResponse(roleServicePort.findRole(id));
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleResponseMapper.toResponseList(roleServicePort.getAllRoles());
    }
}