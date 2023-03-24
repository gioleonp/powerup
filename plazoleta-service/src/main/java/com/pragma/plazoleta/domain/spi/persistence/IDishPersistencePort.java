package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dish);

    DishModel findDishById(Long id);

    List<DishModel> getAllDishes();
    DishModel updateDish(DishModel dishModel);
    DishModel updateActive(DishModel dishModel);
    List<DishModel> getDishesByRestaurantWithPaginationByCategory(Long idRestaurant, int page, int size);
}
