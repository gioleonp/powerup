package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.usecase.RestaurantUseCaseDataTest;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class IRestaurantRepositoryTest {

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

        assertThat(restaurantEntityList).isNotNull();
    }
}
