package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserUseCaseTest {

    @InjectMocks UserUseCase underTest;

    @Mock IUserPersistencePort userPersistencePort;

    @Mock IUserPasswordEncoderPort userPasswordEncoderPort;

    UserModel expectedUser =
            new UserModel(
                    1L,
                    "1002192504",
                    "chuty",
                    "bnet",
                    "573004586742",
                    "chuty@example.com",
                    "password",
                    null);

    @Test
    void saveUser() {

        // When
        underTest.saveUser(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
        verify(userPasswordEncoderPort).encode(any(String.class));
    }

    @Test
    void findUserByEmail() {
        when(userPersistencePort.findUserByEmail(expectedUser.getEmail())).thenReturn(expectedUser);

        // When
        UserModel userModel = underTest.findUserByEmail(expectedUser.getEmail());

        // Then
        assertThat(userModel).isEqualTo(expectedUser);
    }

    @Test
    void findUserById() {
        // Given
        Long id = 1L;

        // When
        underTest.findUserById(id);

        // Then
        verify(userPersistencePort).findUserById(id);
    }

    @Test
    void getAllUsers() {
        // When
        underTest.getAllUsers();

        // Then
        verify(userPersistencePort).getAllUsers();
    }

    @Test
    void createAdmin() {
        // Given
        UserModel user = new UserModel();
        user.setDocumentoDeIdentidad("1002192504");
        user.setNombre("chuty");
        user.setApellido("bnet");
        user.setEmail("chuty@example.com");
        user.setCelular("573004586742");
        user.setContrasenia("password");

        // When
        underTest.createAdmin(user);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createProprietary() {
        // Given
        UserModel user = new UserModel();
        user.setDocumentoDeIdentidad("1002192504");
        user.setNombre("chuty");
        user.setApellido("bnet");
        user.setEmail("chuty@example.com");
        user.setCelular("573004586742");
        user.setContrasenia("password");

        // When
        underTest.createProprietary(user);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createEmployee() {
        // Given
        UserModel user = new UserModel();
        user.setDocumentoDeIdentidad("1002192504");
        user.setNombre("chuty");
        user.setApellido("bnet");
        user.setEmail("chuty@example.com");
        user.setCelular("573004586742");
        user.setContrasenia("password");

        // When
        underTest.createEmployee(user);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createClient() {
        // Given
        UserModel user = new UserModel();
        user.setDocumentoDeIdentidad("1002192504");
        user.setNombre("chuty");
        user.setApellido("bnet");
        user.setEmail("chuty@example.com");
        user.setCelular("573004586742");
        user.setContrasenia("password");

        // When
        underTest.createClient(user);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }
}
