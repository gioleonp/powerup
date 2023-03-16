package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.UserModel;


import java.util.List;

public interface IUserServicePort {
    void saveUser(UserModel userModel);

    UserModel findUserByEmail(String email);
    UserModel findUserById(Long id);

    List<UserModel> getAllUsers();
}