package com.pragma.usermicroservice.domain.usecase;

import com.pragma.usermicroservice.domain.api.IUserServicePort;
import com.pragma.usermicroservice.domain.model.UserModel;
import com.pragma.usermicroservice.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(UserModel userModel) {
        userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel findByEmail(String email) {
        return this.userPersistencePort.findByEmail(email);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}