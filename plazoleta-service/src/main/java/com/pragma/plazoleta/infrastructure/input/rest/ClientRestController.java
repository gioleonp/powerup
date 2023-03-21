package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cliente")
public class ClientRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;

}
