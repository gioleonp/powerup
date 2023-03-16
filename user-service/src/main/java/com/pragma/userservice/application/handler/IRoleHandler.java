package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.request.RoleRequestDto;
import com.pragma.userservice.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {

    void saveRole(RoleRequestDto roleRequestDto);

    RoleResponseDto findRole(int id);

    List<RoleResponseDto> getAllRoles();
}