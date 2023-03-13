package com.pragma.usermicroservice.domain.spi;

import com.pragma.usermicroservice.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);

    UserModel findByEmail(String email);

    List<UserModel> getAllUsers();
}