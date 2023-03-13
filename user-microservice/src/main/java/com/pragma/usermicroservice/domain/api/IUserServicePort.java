package com.pragma.usermicroservice.domain.api;

import com.pragma.usermicroservice.domain.model.UserModel;


import java.util.List;

public interface IUserServicePort {
    void saveUser(UserModel userModel);

    UserModel findByEmail(String email);

    List<UserModel> getAllUsers();
}