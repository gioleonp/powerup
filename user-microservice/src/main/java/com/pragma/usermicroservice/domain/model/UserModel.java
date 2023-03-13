package com.pragma.usermicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String identificationDocument;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private RoleModel role;
}
