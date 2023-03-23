package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DishUseCaseTest {
    @InjectMocks DishUseCase underTest;

    @Mock IDishPersistencePort dishPersistencePort;
    @Mock IRestaurantServicePort restaurantServicePort;
    @Mock IUserServiceCommunicationPort userServiceCommunicationPort;
    @Mock IRestaurantPersistencePort restaurantPersistencePort;

    DishModel expectedDishModel =
            new DishModel(
                    1,
                    "macarrones",
                    new CategoryModel(1, "pastas", ""),
                    "macarrones con queso",
                    20,
                    null,
                    "imagen.url",
                    true);

    RestaurantModel expectedRestaurant =
            new RestaurantModel(
                    1L,
                    "donde keylly",
                    "piedra de bolivar",
                    1L,
                    "573058388527",
                    "http.foto.com",
                    "1000000");

    Long idProprietary = 1L;

    @Test
    void saveDishOk() {

        Long idProprietary = 1L;

        RestaurantModel expectedRestaurant =
                new RestaurantModel(
                        1L,
                        "donde keylly",
                        "piedra de bolivar",
                        1L,
                        "573058388527",
                        "http.foto.com",
                        "1000000");

        // Given
        expectedDishModel.setRestaurante(expectedRestaurant);

        when(restaurantServicePort.findRestaurantById(expectedRestaurant.getId()))
                .thenReturn(expectedRestaurant);

        // Then
        underTest.saveDish(idProprietary, expectedDishModel);

        verify(dishPersistencePort).saveDish(expectedDishModel);
        verify(restaurantServicePort).findRestaurantById(expectedRestaurant.getId());
    }

    @Test
    void saveDishNotCorrectProprietary() {

        Long idProprietary = 2L;

        // Given
        expectedDishModel.setRestaurante(expectedRestaurant);

        when(restaurantServicePort.findRestaurantById(expectedRestaurant.getId()))
                .thenReturn(expectedRestaurant);

        // Then
        assertThatExceptionOfType(ProprietaryNotMatchException.class)
                .isThrownBy(() -> underTest.saveDish(idProprietary, expectedDishModel))
                .withMessageMatching("USER NOT AUTHORIZED");
    }

    @Test
    void UpdateDishOk() {
        // Given
        UserModel foundUserModel = new UserModel();
        foundUserModel.setId(idProprietary);

        expectedDishModel.setRestaurante(expectedRestaurant);

        when(dishPersistencePort.findDishById(expectedDishModel.getId()))
                .thenReturn(expectedDishModel);

        when(userServiceCommunicationPort.findUserById(idProprietary)).thenReturn(foundUserModel);

        // When
        underTest.updateDish(idProprietary, expectedDishModel.getId(), expectedDishModel);

        // Then
        verify(dishPersistencePort).updateDish(expectedDishModel);
        verify(userServiceCommunicationPort).findUserById(idProprietary);
    }

    @Test
    void UpdateDishWithWrongProprietary() {
        // Given
        UserModel foundUserModel = new UserModel();
        foundUserModel.setId(2L);

        expectedDishModel.setRestaurante(expectedRestaurant);

        when(dishPersistencePort.findDishById(expectedDishModel.getId()))
                .thenReturn(expectedDishModel);

        when(userServiceCommunicationPort.findUserById(idProprietary)).thenReturn(foundUserModel);

        // When
        assertThatExceptionOfType(ProprietaryNotMatchException.class)
                .isThrownBy(
                        () ->
                                underTest.updateDish(
                                        idProprietary,
                                        expectedDishModel.getId(),
                                        expectedDishModel))
                .withMessageMatching("USER NOT AUTHORIZED");
    }
}
