package com.pragma.plazoleta.domain.spi.servicecommunication;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.model.UserModel;

import java.util.List;


public interface IUserServiceCommunicationPort {

    UserModel findUserById(long id);
    UserModel findFullUserByEmail(String email);
    UserModel findByEmail(String email);
    void createProprietary(UserRequestDto userRequestDto);

    List<UserModel> getAllUsers();

}
