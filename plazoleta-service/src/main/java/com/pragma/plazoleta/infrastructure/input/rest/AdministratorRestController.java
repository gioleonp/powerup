package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.application.mapper.IUserResponseMapper;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "Save proprietary")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Proprietary saved",
                        content = @Content)
            })
    @PostMapping("/propietario")
    public ResponseEntity<Void> createProprietary(@RequestBody UserModel userRequestDto) {
        userServiceCommunicationPort.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All users found",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No data found",
                            content = @Content)
            })
    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserModel> userModels = userServiceCommunicationPort.getAllUsers();
        return ResponseEntity.ok(userResponseMapper.toResponseList(userModels));
    }
}
