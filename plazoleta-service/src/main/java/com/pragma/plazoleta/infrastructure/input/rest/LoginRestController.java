package com.pragma.plazoleta.infrastructure.input.rest;


import com.pragma.plazoleta.infrastructure.security.dto.AuthCredentialsDto;
import com.pragma.plazoleta.infrastructure.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/")
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody AuthCredentialsDto authCredentials){
        return ResponseEntity.ok(loginService.login(authCredentials));
    }

}
