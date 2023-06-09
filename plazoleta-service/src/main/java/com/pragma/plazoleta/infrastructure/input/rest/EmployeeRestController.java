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
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/empleado")
public class EmployeeRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Save employee")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Employee saved",
                        content = @Content)
            })
    @PostMapping("/new")
    public ResponseEntity<Void> createEmployee(
            @Valid @RequestBody UserModel userRequestDto,
            @RequestParam("proprietary") Long idProprietary,
            @RequestParam("restaurant") Long idRestaurant) {
        employeeHandler.saveEmployee(userRequestDto, idProprietary, idRestaurant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
