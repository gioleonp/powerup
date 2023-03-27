package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
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
class RestaurantRepositoryTest {

    @Autowired IRestaurantRepository underTest;

    @BeforeEach
    void init() {
        RestaurantEntity restaurant = RestaurantRepositoryDataTest.getRestaurant();
        underTest.save(restaurant);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAllRestaurantsWithPagination() {
        // Given
        Pageable pageable = PageRequest.of(0, 5);

        List<RestaurantEntity> restaurantEntityList =
                underTest.findAllRestaurantsWithPagination(pageable).toList();

        assertThat(restaurantEntityList.size()).isGreaterThan(0);
    }
}
