package com.pragma.userservice.domain.spi.persistence;

import com.pragma.userservice.domain.model.UserModel;

import java.util.List;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel userModel);
    UserModel findUserByEmail(String email);
    UserModel findUserById(Long id);
    List<UserModel> getAllUsers();
}