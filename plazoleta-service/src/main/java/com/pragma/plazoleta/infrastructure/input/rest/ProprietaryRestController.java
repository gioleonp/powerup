package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/propietario")
public class ProprietaryRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;

    @PostMapping("")
    public ResponseEntity<Void> createProprietary(@RequestBody UserRequestDto userRequestDto){
        userServiceCommunicationPort.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
