package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto findRestaurantById(Long id);

    List<RestaurantResponseDto> getAllRestaurants();

    List<RestaurantResponseNameAndUrlDto> getRestaurantsWithPagination(int offset, int count);
}
