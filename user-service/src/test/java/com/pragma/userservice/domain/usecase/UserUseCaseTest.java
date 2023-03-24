package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserUseCaseTest {

    @InjectMocks UserUseCase underTest;

    @Mock IUserPersistencePort userPersistencePort;

    @Mock IUserPasswordEncoderPort userPasswordEncoderPort;

    @Test
    void saveUser() {
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

        // When
        underTest.saveUser(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
        verify(userPasswordEncoderPort).encode(any(String.class));
    }

    @Test
    void findUserByEmail() {
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

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
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

        // When
        underTest.createAdmin(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createProprietary() {
        // Given
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

        // When
        underTest.createProprietary(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createEmployee() {
        // Given
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

        // When
        underTest.createEmployee(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }

    @Test
    void createClient() {
        // Given
        UserModel expectedUser = UserUseCaseDataTest.getUserModel();

        // When
        underTest.createClient(expectedUser);

        // Then
        verify(userPersistencePort).saveUser(any(UserModel.class));
    }
}
