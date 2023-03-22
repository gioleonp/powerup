package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dish);
    DishModel findDishById(int id);
    List<DishModel> getAllDishes();
    DishModel updateDish(DishModel dishModel);
    DishModel updateActive(DishModel dishModel);
}
