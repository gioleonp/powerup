package com.pragma.userservice.domain.usecase;


import com.pragma.userservice.domain.exception.DomainException;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;


class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IUserPasswordEncoderPort userPasswordEncoderPort;
    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    void saveUser() {

        UserModel userToReturn = new UserModel();
        String encodedPassword = "encodedPassword";

        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(userToReturn);
        when(userPasswordEncoderPort.encode(anyString())).thenReturn(encodedPassword);


        UserModel user = new UserModel();

        user.setNombre("");
        user.setApellido("ramirez");
        user.setDocumentoDeIdentidad("1002192504");
        user.setCelular("573004586762");
        user.setEmail("chuty@gmail.com");
        user.setContrasenia("password123");

        List<String> exceptionsMessage = Arrays.asList("NOMBRE", "APELLIDO",
                "DOCUMENTO", "CELULAR", "EMAIL", "CONTRASENIA");

        try {
            userUseCase.saveUser(user);
        } catch (DomainException de) {
            for (String customException: exceptionsMessage) {
                assertTrue(exceptionsMessage.contains(customException));
            }
        }




    }
}