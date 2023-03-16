package com.pragma.plazoleta.domain.spi.servicecommunication;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;

public interface IUserServiceCommunicationPort {

    UserResponseDto findUser(long id);

}
