package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.application.mapper.IUserResponseMapper;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdministratorRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IUserResponseMapper userResponseMapper;

    @PostMapping("/propietario")
    public ResponseEntity<Void> createProprietary(@RequestBody UserRequestDto userRequestDto){
        userServiceCommunicationPort.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserModel> userModels = userServiceCommunicationPort.getAllUsers();
        return ResponseEntity.ok(userResponseMapper.toResponseList(userModels));
    }


}
