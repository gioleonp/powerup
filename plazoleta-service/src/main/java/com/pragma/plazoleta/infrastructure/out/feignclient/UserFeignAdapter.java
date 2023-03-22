package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.UserResponseWithPasswordDto;
import com.pragma.plazoleta.application.mapper.IUserResponseMapper;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFeignAdapter implements IUserServiceCommunicationPort {

  private final IApiUserService userServiceCommunicationAdapter;
  private final IUserResponseMapper userResponseMapper;

  @Override
  public UserModel findUserById(long id) {
    return userResponseMapper.toModel(userServiceCommunicationAdapter.findUserById(id));
  }

  @Override
  public UserModel findFullUserByEmail(String email) {
    UserResponseWithPasswordDto userResponse =
        userServiceCommunicationAdapter.findFullUserByEmail(email);
    return userResponseMapper.toModelWithPassword(userResponse);
  }

  @Override
  public UserModel findByEmail(String email) {
    return userResponseMapper.toModel(userServiceCommunicationAdapter.findUserByEmail(email));
  }

  @Override
  public void createProprietary(UserRequestDto userRequestDto) {
    userServiceCommunicationAdapter.createProprietary(userRequestDto);
  }

  @Override
  public void createEmployee(UserRequestDto userRequestDto) {
    userServiceCommunicationAdapter.createEmployee(userRequestDto);
  }

  @Override
  public void createClient(UserRequestDto userRequestDto) {
    userServiceCommunicationAdapter.createClient(userRequestDto);
  }

  @Override
  public List<UserModel> getAllUsers() {
    return userResponseMapper.toModelList(userServiceCommunicationAdapter.getAllUsers());
  }
}
