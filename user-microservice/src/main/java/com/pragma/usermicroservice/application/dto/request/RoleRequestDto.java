package com.pragma.usermicroservice.application.dto.request;

import com.pragma.usermicroservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {

    private long id;
    private ERoles name;
    private String description;

    public RoleRequestDto(ERoles name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
