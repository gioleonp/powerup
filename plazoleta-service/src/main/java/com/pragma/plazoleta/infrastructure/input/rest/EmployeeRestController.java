package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IEmployeeHandler;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/empleado")
public class EmployeeRestController {

  private final IUserServiceCommunicationPort userServiceCommunicationPort;
  private final IEmployeeHandler employeeHandler;

  @PostMapping("/")
  public ResponseEntity<Void> createEmployee(
      @RequestBody UserRequestDto userRequestDto,
      @RequestParam("proprietary") Long idProprietary,
      @RequestParam("restaurant") Long idRestaurant) {

    userServiceCommunicationPort.createEmployee(userRequestDto);

    employeeHandler.saveEmployee(userRequestDto, idProprietary, idRestaurant);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
