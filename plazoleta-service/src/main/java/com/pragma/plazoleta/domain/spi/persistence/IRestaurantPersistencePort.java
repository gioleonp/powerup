package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.RestaurantModel;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);
}
