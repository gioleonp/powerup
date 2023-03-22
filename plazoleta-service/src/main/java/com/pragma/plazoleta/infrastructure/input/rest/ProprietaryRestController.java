package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IEmployeeHandler;
import com.pragma.plazoleta.application.handler.IProprietaryHandler;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/propietario")
public class ProprietaryRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IEmployeeHandler employeeHandler;

    @PostMapping("")
    public ResponseEntity<Void> createProprietary(@RequestBody UserRequestDto userRequestDto){
        userServiceCommunicationPort.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
