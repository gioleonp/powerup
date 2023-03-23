package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPasswordEncoderPort passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPasswordEncoderPort passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void saveUser(UserModel userModel) {

        // Giving format to the phoneNumber
        String correctedPhoneNumber = userModel.getCelular().contains("+")
        ? userModel.getCelular()
        : "+" + userModel.getCelular();

        userModel.setCelular(correctedPhoneNumber);

        // Encrypting
        userModel.setContrasenia(passwordEncoder.encode(userModel.getContrasenia()));

        userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return userPersistencePort.findUserByEmail(email);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userPersistencePort.findUserById(id);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public void createAdmin(UserModel userModel) {
        userModel.setRol(new RoleModel(1));
        saveUser(userModel);
    }

    @Override
    public void createProprietary(UserModel userModel) {
        userModel.setRol(new RoleModel(2));
        saveUser(userModel);
    }

    @Override
    public void createEmployee(UserModel userModel) {
        userModel.setRol(new RoleModel(4));
        saveUser(userModel);
    }

    @Override
    public void createClient(UserModel userModel) {
        userModel.setRol(new RoleModel(3));
        saveUser(userModel);
    }
}