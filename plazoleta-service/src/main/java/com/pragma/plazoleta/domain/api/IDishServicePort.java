package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    void saveDish(DishModel dish);
    DishModel findDishById(int id);
    List<DishModel> getAllDishes();
    void updateDish(Long id_proprietary, int id_dish, DishModel dishModel);

}
