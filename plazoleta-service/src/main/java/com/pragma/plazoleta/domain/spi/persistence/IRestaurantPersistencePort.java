package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
    List<RestaurantModel> getAllRestaurants();
}
