package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.request.RoleRequestDto;
import com.pragma.userservice.application.dto.request.UserRequestDto;
import com.pragma.userservice.application.dto.response.UserResponseDtoWithPassword;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.application.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("save")
    public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userHandler.findUserByEmail(email));
    }

    @GetMapping("/user/full/{email}")
    public ResponseEntity<UserResponseDtoWithPassword> findFullUserByEmail(
    @PathVariable("email") String email){

        return ResponseEntity.ok(userHandler.findFullUserByEmail(email));

    }


    @GetMapping("/user/id/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userHandler.findUserById(id));
    }


    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/admin")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userHandler.getAllUsers());
    }


    @Operation(summary = "Add a new admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Admin already exists", content = @Content)
    })
    @PostMapping("/admin")
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.createAdmin(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Add a new proprietary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Proprietary created",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Proprietary already exists",
                    content = @Content)
    })
    @PostMapping("/admin/proprietary")
    public ResponseEntity<Void> createProprietary(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.createProprietary(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Client created",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Client already exists",
                    content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> createClient(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.createClient(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Employee created",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Employee already exists",
                    content = @Content)
    })
    @PostMapping("/proprietary/employee")
    public ResponseEntity<Void> createEmployee(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.createEmployee(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}