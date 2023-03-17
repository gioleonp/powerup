package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dish);
    List<DishModel> getAllDishes();

}
