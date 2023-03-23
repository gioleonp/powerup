package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.pragma.plazoleta.application.mapper.IRestaurantResponseNameAndUrlMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantHandlerImpl implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantResponseNameAndUrlMapper restaurantResponseNameAndUrlMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public RestaurantResponseDto findRestaurantById(Long id) {
        RestaurantModel restaurantModel = restaurantServicePort.findRestaurantById(id);
        return restaurantResponseMapper.toResponse(restaurantModel);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public List<RestaurantResponseNameAndUrlDto> getRestaurantsWithPagination(
            int offset, int count) {
        List<RestaurantNameAndUrlModel> restaurants =
                restaurantServicePort.getRestaurantsWithPagination(offset, count);

        return restaurantResponseNameAndUrlMapper.toRestaurantResponseNameAndUrlList(restaurants);
    }
}
