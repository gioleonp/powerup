package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.domain.model.OrderModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.usecase.OrderUseCaseDataTest;
import com.pragma.plazoleta.domain.usecase.RestaurantUseCaseDataTest;
import com.pragma.plazoleta.domain.usecase.UserDataTest;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired private IOrderRepository orderRepository;

    @Autowired private IRestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        List<OrderEntity> orderEntities = OrderRepositoryDataTest.getAllOrders();
        RestaurantEntity restaurant = RestaurantRepositoryDataTest.getRestaurant();

        restaurantRepository.save(restaurant);
        orderRepository.saveAll(orderEntities);
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        restaurantRepository.deleteAll();
    }

    @Test
    void findAllOrderByStateAndRestaurantPreparing() {
        RestaurantModel restaurantModel = RestaurantUseCaseDataTest.getRestaurantModel();
        Pageable pageable = PageRequest.of(0, 5);

        // When
        List<OrderEntity> orders =
                orderRepository
                        .findAllOrderByStateAndRestaurant(
                                EOrderState.EN_PREPARACION.toString(),
                                restaurantModel.getId(),
                                pageable)
                        .toList();

        // Then
        assertThat(orders.size()).isEqualTo(1);
        assertThat(orders.get(0).getEstado()).isEqualTo(EOrderState.EN_PREPARACION);
    }

    @Test
    void findAllOrderByStateAndRestaurantPending() {
        RestaurantModel restaurantModel = RestaurantUseCaseDataTest.getRestaurantModel();
        Pageable pageable = PageRequest.of(0, 5);

        // When
        List<OrderEntity> orders =
                orderRepository
                        .findAllOrderByStateAndRestaurant(
                                EOrderState.PENDIENTE.toString(), restaurantModel.getId(), pageable)
                        .toList();

        // Then
        assertThat(orders.size()).isEqualTo(1);
        assertThat(orders.get(0).getEstado()).isEqualTo(EOrderState.PENDIENTE);
    }

    @Test
    void getNumberOfOrdersWithStateInPreparationPendingOrReadyEqualsTwo() {
        // Given
        UserModel client = UserDataTest.getUserModel();

        // When
        int numberOfOrders =
                orderRepository.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        client.getId());

        // Then
        assertThat(numberOfOrders).isEqualTo(2);
    }

    @Test
    void getNumberOfOrdersWithStateInPreparationPendingOrReadyEqualsZero() {
        // Given
        UserModel client = UserDataTest.getUserModel();
        orderRepository.deleteAll();

        // When
        int numberOfOrders =
                orderRepository.getNumberOfOrdersWithStateInPreparationPendingOrReady(
                        client.getId());

        // Then
        assertThat(numberOfOrders).isEqualTo(0);
    }
}
