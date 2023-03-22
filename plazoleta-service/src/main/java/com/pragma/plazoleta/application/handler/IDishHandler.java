package com.pragma.plazoleta.application.handler;


import com.pragma.plazoleta.application.dto.request.DishRequestPriceAndDescriptionDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    void saveDish(Long id_proprietary, DishRequestPriceAndDescriptionDto dishRequestDto);
    DishResponseDto findDishById(int id);
    List<DishResponseDto> getAllDishes();
    void updateDish(
            Long id_proprietary,
            int id_dish,
            DishRequestPriceAndDescriptionDto dishRequestDto);
    DishResponseDto updateActive(boolean active, Long idProprietary, int idDish);


}
