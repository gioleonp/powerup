package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.handler.IEmployeeHandler;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/propietario")
public class ProprietaryRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IEmployeeHandler employeeHandler;

    /*
    @Operation(summary = "Save employee")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Employee saved",
                        content = @Content)
            })
    @PostMapping("/empleado")
    public ResponseEntity<Void> createEmployee(
            @RequestBody UserModel userRequestDto,
            @RequestParam("proprietary") Long idProprietary,
            @RequestParam("restaurant") Long idRestaurant) {

        userServiceCommunicationPort.createEmployee(userRequestDto);

        employeeHandler.saveEmployee(userRequestDto, idProprietary, idRestaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

     */
}
