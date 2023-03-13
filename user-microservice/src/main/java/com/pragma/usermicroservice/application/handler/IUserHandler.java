package com.pragma.usermicroservice.application.handler;

import com.pragma.usermicroservice.application.dto.request.UserRequestDto;
import com.pragma.usermicroservice.application.dto.response.UserResponseDto;
import com.pragma.usermicroservice.domain.model.UserModel;

import java.util.List;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);

    UserModel findByEmail(String email);

    List<UserResponseDto> getAllUsers();
}