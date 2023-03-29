package com.pragma.plazoleta.domain.spi.servicecommunication;

import com.pragma.plazoleta.domain.model.UserModel;

import java.util.List;

public interface IUserServiceCommunicationPort {

    UserModel findUserById(long id);

    UserModel findFullUserByEmail(String email);

    UserModel findByEmail(String email);

    void createProprietary(UserModel userRequestDto);

    void createEmployee(UserModel userRequestDto);

    void createClient(UserModel userRequestDto);

    List<UserModel> getAllUsers();
}
