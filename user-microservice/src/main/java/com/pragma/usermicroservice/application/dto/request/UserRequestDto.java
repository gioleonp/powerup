package com.pragma.usermicroservice.application.dto.request;

import com.pragma.usermicroservice.domain.model.ERoles;
import com.pragma.usermicroservice.domain.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String identificationDocument;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
}
