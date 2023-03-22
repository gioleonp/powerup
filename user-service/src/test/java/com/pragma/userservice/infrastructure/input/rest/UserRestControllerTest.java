package com.pragma.userservice.infrastructure.input.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.userservice.application.dto.request.UserRequestDto;
import com.pragma.userservice.application.handler.IUserHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.config.additional-location=classpath:application.yml")
class UserRestControllerTest {
    @MockBean
    private UserRestController userRestController;

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private String base_url = "/api/v1";

    @Test
    void createProprietaryOk() throws Exception {
        UserRequestDto request = new UserRequestDto(
                "1002192504", "gio prueba",
                "leon prueba", "573111111111",
                "prueba@gmail.com", "12345"
        );

        mvc.perform(MockMvcRequestBuilders
                .post(base_url + "/admin/proprietary")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    void createProprietaryEmptyEmail() throws Exception {
        UserRequestDto request = new UserRequestDto(
                "1002192504", "gio prueba",
                "leon prueba", "573111111111",
                "", "12345"
        );

        mvc.perform(MockMvcRequestBuilders
                        .post(base_url + "/admin/proprietary")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
}