package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.domain.model.UserModel;import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;

    @Operation(summary = "Add a new client")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Client created",
                        content = @Content),
                @ApiResponse(
                        responseCode = "409",
                        description = "Client already exists",
                        content = @Content)
            })
    @PostMapping("/register")
    public ResponseEntity<Void> createClient(@RequestBody UserModel userDto) {
        userServiceCommunicationPort.createClient(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
