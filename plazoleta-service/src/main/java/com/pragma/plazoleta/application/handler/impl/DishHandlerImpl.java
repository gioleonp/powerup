package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.mapper.IDishRequestMapper;
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

    @Override
    public void saveDish(Long id_proprietary, DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDishModel(dishRequestDto);
        dishServicePort.saveDish(id_proprietary, dishModel);
    }

    @Override
    public DishResponseDto findDishById(int id) {
        return dishResponseMapper.toResponse(dishServicePort.findDishById(id));
    }

    @Override
    public List<DishResponseDto> getAllDishes() {
        List<DishModel> dishModelList = dishServicePort.getAllDishes();
        return dishResponseMapper.toResponseList(dishModelList);
    }


    @Override
    public void updateDish(Long id_proprietary, int id_dish, DishRequestDto dishRequestDto) {
        DishModel dishModel = dishRequestMapper.toDishModel(dishRequestDto);
        dishServicePort.updateDish(id_proprietary, id_dish, dishModel);
    }


    @Override
    public DishResponseDto updateActive(boolean active, Long idProprietary, int idDish) {
        DishModel dishModel = dishServicePort.updateActive(active, idProprietary, idDish);
        return dishResponseMapper.toResponse(dishModel);
    }
}
