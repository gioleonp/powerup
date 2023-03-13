package com.pragma.usermicroservice.application.dto.request;

import com.pragma.usermicroservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {
    private ERoles name;
    private String description;

    public RoleRequestDto(ERoles name, String description){
        this.name = name;
        this.description = description;
    }
}
