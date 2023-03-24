package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.request.DishRequestPriceAndDescriptionDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.mapper.IDishRequestMapper;
import com.pragma.plazoleta.application.mapper.IDishRequestPriceAndDescriptionMapper;
import com.pragma.plazoleta.application.mapper.IDishResponseMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DishHandlerImpl implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;
    private final IDishRequestPriceAndDescriptionMapper dishRequestPriceAndDescriptionMapper;

    @Override
    public void saveDish(Long id_proprietary, DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDishModel(dishRequestDto);
        dishServicePort.saveDish(id_proprietary, dishModel);
    }

    @Override
    public DishResponseDto findDishById(Long id) {
        return dishResponseMapper.toResponse(dishServicePort.findDishById(id));
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        List<DishModel> dishModelList = dishServicePort.getAllDishes();
        return dishResponseMapper.toResponseList(dishModelList);
    }

    @Override
    public void updateDish(
            Long id_proprietary, Long id_dish, DishRequestPriceAndDescriptionDto dishRequestDto) {
        DishModel dishModel = dishRequestPriceAndDescriptionMapper.toModel(dishRequestDto);
        dishServicePort.updateDish(id_proprietary, id_dish, dishModel);
    }

    @Override
    public DishResponseDto updateActive(boolean active, Long idProprietary, Long idDish) {
        DishModel dishModel = dishServicePort.updateActive(active, idProprietary, idDish);
        return dishResponseMapper.toResponse(dishModel);
    }

    @Override
    public List<DishResponseDto> getDishesByRestaurantWithPaginationByCategory(
            Long idRestaurant, int page, int size) {

        List<DishModel> dishModelList =
                dishServicePort.getDishesByRestaurantWithPaginationByCategory(
                        idRestaurant, page, size);
        return dishResponseMapper.toResponseList(dishModelList);
    }
}
