package com.pragma.userservice.application.dto.request;

import com.pragma.userservice.domain.model.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDto {

    private int id;
    private ERoles nombre;
    private String descripcion;

    public RoleRequestDto(ERoles nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public RoleRequestDto(int id){
        this.id = id;
    }
}
