package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RolModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RestaurantUseCaseTest {

    @InjectMocks RestaurantUseCase underTest;

    @Mock IRestaurantPersistencePort restaurantPersistencePort;

    @Mock IUserServiceCommunicationPort userServiceCommunicationPort;

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
    void saveRestaurantOk() {
        expectedUser.setRol(new RolModel(2, "ROLE_PROPIETARIO", "lo que sea"));

        when(userServiceCommunicationPort.findUserById(expectedUser.getId()))
                .thenReturn(expectedUser);

        // Given
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setNombre("Restaurante prueba");
        restaurantModel.setDireccion("Direccion prueba");
        restaurantModel.setTelefono("573004586742");
        restaurantModel.setIdPropietario(1L);
        restaurantModel.setUrlLogo("http://url.com");
        restaurantModel.setNit("1000000");

        // When
        underTest.saveRestaurant(restaurantModel);

        // Then
        verify(restaurantPersistencePort).saveRestaurant(restaurantModel);
        verify(userServiceCommunicationPort).findUserById(restaurantModel.getIdPropietario());
    }

    @Test
    void saveRestaurantNotProprietary() {
        expectedUser.setRol(new RolModel(3, "ROLE_CLIENTE", "lo que sea"));

        when(userServiceCommunicationPort.findUserById(expectedUser.getId()))
                .thenReturn(expectedUser);

        // Given
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setNombre("Restaurante prueba");
        restaurantModel.setDireccion("Direccion prueba");
        restaurantModel.setTelefono("573004586742");
        restaurantModel.setIdPropietario(1L);
        restaurantModel.setUrlLogo("http://url.com");
        restaurantModel.setNit("1000000");

        // Then
        assertThatExceptionOfType(DomainException.class)
                .isThrownBy(() -> underTest.saveRestaurant(restaurantModel))
                .withMessageMatching("USER NOT AUTHORIZED");
    }

    @Test
    void findRestaurantById() {



    }

    @Test
    void getAllRestaurants() {}

    @Test
    void getRestaurantsWithPagination() {}
}
