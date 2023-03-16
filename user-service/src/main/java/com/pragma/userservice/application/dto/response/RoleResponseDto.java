package com.pragma.userservice.application.dto.response;

import com.pragma.userservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private int id;
    private ERoles name;
    private String description;
}
