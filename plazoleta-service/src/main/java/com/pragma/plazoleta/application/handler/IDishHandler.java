package com.pragma.plazoleta.application.handler;


import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    void saveDish(Long id_proprietary, DishRequestDto dishRequestDto);
    DishResponseDto findDishById(int id);
    List<DishResponseDto> getAllDishes();
    void updateDish(Long id_proprietary, int id_dish, DishRequestDto dishRequestDto);


}
