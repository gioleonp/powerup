package com.pragma.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RoleModel {
    private int id;
    private ERoles name;
    private String description;

    public RoleModel(int id){
        this.id = id;
    }
}
