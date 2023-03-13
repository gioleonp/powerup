package com.pragma.usermicroservice.application.handler;

import com.pragma.usermicroservice.application.dto.request.RoleRequestDto;
import com.pragma.usermicroservice.application.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {

    void saveRole(RoleRequestDto roleRequestDto);

    RoleResponseDto findRole(Long id);

    List<RoleResponseDto> getAllRoles();
}