package com.pragma.userservice.application.dto.request;

import com.pragma.userservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {

    private int id;
    private ERoles name;
    private String description;

    public RoleRequestDto(ERoles name, String description){
        this.name = name;
        this.description = description;
    }

    public RoleRequestDto(int id){
        this.id = id;
    }
}