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
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/propietario")
public class ProprietaryRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Save proprietary")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Proprietary saved",
                        content = @Content)
            })
    @PostMapping("")
    public ResponseEntity<Void> createProprietary(@Valid @RequestBody UserModel userRequestDto) {
        userServiceCommunicationPort.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
