package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.infrastructure.security.dto.AuthCredentialsDto;
import com.pragma.plazoleta.infrastructure.security.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class LoginRestController {

    private final LoginService loginService;

    @Operation(summary = "Login")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Login successful",
                        content = @Content)
            })
    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthCredentialsDto authCredentials) {
        return ResponseEntity.ok(loginService.login(authCredentials));
    }
}
