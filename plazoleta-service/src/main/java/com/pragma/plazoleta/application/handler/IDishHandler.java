package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;import com.pragma.plazoleta.application.dto.request.DishRequestPriceAndDescriptionDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    void saveDish(Long idProprietary, DishRequestDto dishRequestDto);

    DishResponseDto findDishById(Long id);

    List<DishResponseDto> getAllDishes();

    void updateDish(
            Long id_proprietary, Long id_dish, DishRequestPriceAndDescriptionDto dishRequestDto);

    DishResponseDto updateActive(boolean active, Long idProprietary, Long idDish);

    List<DishResponseDto> getDishesByRestaurantWithPaginationByCategory(
            Long idRestaurant, int page, int size);
}
