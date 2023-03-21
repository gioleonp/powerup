package com.pragma.userservice.application.handler.impl;

import com.pragma.userservice.application.dto.request.UserRequestDto;
import com.pragma.userservice.application.dto.response.UserResponseDtoWithPassword;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.application.mapper.IUserRequestMapper;
import com.pragma.userservice.application.mapper.IUserResponseMapper;
import com.pragma.userservice.application.dto.response.UserResponseDto;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userServicePort.saveUser(userModel);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) throws RuntimeException {
        return userResponseMapper.toResponse(userServicePort.findUserByEmail(email));
    }

    @Override
    public UserResponseDtoWithPassword findFullUserByEmail(String email) {
        return userResponseMapper.toResponseWithPassword(userServicePort.findUserByEmail(email));
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        return userResponseMapper.toResponse(userServicePort.findUserById(id));
    }


    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public void createAdmin(UserRequestDto userRequestDto) {
        userServicePort.createAdmin(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createProprietary(UserRequestDto userRequestDto) {
        userServicePort.createProprietary(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createEmployee(UserRequestDto userRequestDto) {
        userServicePort.createEmployee(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createClient(UserRequestDto userRequestDto) {
        userServicePort.createClient(userRequestMapper.toUser(userRequestDto));
    }
}