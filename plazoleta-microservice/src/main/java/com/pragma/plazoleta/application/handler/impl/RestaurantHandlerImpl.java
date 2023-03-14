package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.handler.ICategoryHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private IRestaurantServicePort restaurantServicePort;
    private IRestaurantResponseMapper restaurantResponseMapper;
    private IRestaurantRequestMapper restaurantRequestMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel =
                restaurantRequestMapper.toRestaurant(restaurantRequestDto);

        restaurantServicePort.saveRestaurant(restaurantModel);
    }
}
