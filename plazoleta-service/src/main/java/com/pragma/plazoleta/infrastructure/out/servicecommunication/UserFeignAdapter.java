package com.pragma.plazoleta.infrastructure.out.servicecommunication;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserFeignAdapter implements IUserServiceCommunicationPort {

    private IApiUserService userServiceCommunicationAdapter;

    @Override
    public UserResponseDto findUser(long id) {
        return userServiceCommunicationAdapter.findUserById(id);
    }
}
