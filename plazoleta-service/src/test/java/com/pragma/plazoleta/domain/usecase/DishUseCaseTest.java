package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;import com.pragma.plazoleta.domain.model.CategoryModel;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DishUseCaseTest {
    @InjectMocks DishUseCase underTest;

    @Mock IDishPersistencePort dishPersistencePort;
    @Mock IRestaurantServicePort restaurantServicePort;
    @Mock IUserServiceCommunicationPort serviceCommunicationPort;
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
    void saveDishNotProprietary() {

        Long idProprietary = 2L;

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
        assertThatExceptionOfType(ProprietaryNotMatchException.class).isThrownBy(() ->
            underTest.saveDish(idProprietary, expectedDishModel)
        ).withMessageMatching("USER NOT AUTHORIZED");
    }
}
