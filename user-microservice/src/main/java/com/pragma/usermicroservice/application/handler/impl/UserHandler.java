package com.pragma.usermicroservice.application.handler.impl;

import com.pragma.usermicroservice.application.dto.request.UserRequestDto;
import com.pragma.usermicroservice.application.handler.IUserHandler;
import com.pragma.usermicroservice.application.mapper.IUserRequestMapper;
import com.pragma.usermicroservice.application.mapper.IUserResponseMapper;
import com.pragma.usermicroservice.application.dto.response.UserResponseDto;
import com.pragma.usermicroservice.domain.api.IUserServicePort;
import com.pragma.usermicroservice.domain.model.UserModel;
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
    public UserModel findByEmail(String email) throws RuntimeException {
        return userServicePort.findByEmail(email);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }
}