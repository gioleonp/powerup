package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Long id_proprietary, DishModel dish);

    DishModel findDishById(Long id);

    List<DishModel> getAllDishes();

    void updateDish(Long id_proprietary, Long id_dish, DishModel dishModel);

    DishModel updateActive(boolean active, Long idProprietary, Long idDish);

    List<DishModel> getDishesByRestaurantWithPaginationByCategory(Long idRestaurant, int page, int size);

}
