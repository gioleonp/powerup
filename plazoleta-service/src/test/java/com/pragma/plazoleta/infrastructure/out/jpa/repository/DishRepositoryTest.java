package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DishRepositoryTest {

    @Autowired private IDishRepository underTest;
    @Autowired private IRestaurantRepository restaurantRepository;
    @Autowired private ICategoryRepository categoryRepository;

    @BeforeEach
    void init() {
        CategoryEntity categoryArroz = DishRepositoryDataTest.getDishes().get(1).getCategoria();
        CategoryEntity categoryJugo = DishRepositoryDataTest.getDishes().get(0).getCategoria();
        RestaurantEntity restaurantEntity =
                DishRepositoryDataTest.getDishes().get(0).getRestaurante();
        List<DishEntity> dishEntityList = DishRepositoryDataTest.getDishes();

        categoryRepository.saveAll(Arrays.asList(categoryArroz, categoryJugo)); // save category
        restaurantRepository.save(restaurantEntity); // First save restaurant
        underTest.saveAll(dishEntityList); // Save dishes
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        restaurantRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void findAllRestaurantsWithPaginationOk() {

        // Given
        int page = 0;
        int size = 10;

        List<DishEntity> expectedDishes = DishRepositoryDataTest.getDishes();

        Long idRestaurant = DishRepositoryDataTest.getDishes().get(0).getRestaurante().getId();
        Pageable pageable = PageRequest.of(page, size);

        // When
        Page<DishEntity> dishesPage =
                underTest.findDishByRestaurantWithPaginationByCategory(idRestaurant, pageable);
        List<DishEntity> dishesList = dishesPage.toList();

        // Then
        assertThat(dishesList.size()).isEqualTo(2);
        assertThat(dishesList.get(0).getCategoria().getNombre())
                .isEqualTo(expectedDishes.get(1).getCategoria().getNombre());
    }
}
