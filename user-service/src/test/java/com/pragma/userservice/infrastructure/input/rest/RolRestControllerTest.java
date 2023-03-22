package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.handler.IRoleHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class RolRestControllerTest {

    @MockBean
    private RolRestController rolRestController;

    @Mock
    private IRoleHandler roleHandler;

    @Test
    void getAllObjects() {
    }
}