package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.request.UserRequestDto;
import com.pragma.userservice.application.dto.response.UserResponseDto;
import com.pragma.userservice.application.dto.response.UserResponseDtoWithPassword;
import com.pragma.userservice.domain.model.UserModel;

import java.util.List;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    UserResponseDto findUserByEmail(String email);
    UserResponseDtoWithPassword findFullUserByEmail(String email);
    UserResponseDto findUserById(Long id);
    List<UserResponseDto> getAllUsers();
    void createAdmin(UserRequestDto userRequestDto);
    void createProprietary(UserRequestDto userRequestDto);
    void createEmployee(UserRequestDto userRequestDto);
    void createClient(UserRequestDto userRequestDto);
}