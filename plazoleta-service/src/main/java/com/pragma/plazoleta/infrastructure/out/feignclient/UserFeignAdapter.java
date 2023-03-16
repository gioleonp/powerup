package com.pragma.plazoleta.infrastructure.out.feignclient;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFeignAdapter implements IUserServiceCommunicationPort {

    private final IApiUserService userServiceCommunicationAdapter;

    @Override
    public UserResponseDto findUser(long id) {
        return userServiceCommunicationAdapter.findUserById(id);
    }
}
