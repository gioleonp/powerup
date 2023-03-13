package com.pragma.usermicroservice.application.dto.response;

import com.pragma.usermicroservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private Long id;
    private ERoles name;
    private String description;
}
