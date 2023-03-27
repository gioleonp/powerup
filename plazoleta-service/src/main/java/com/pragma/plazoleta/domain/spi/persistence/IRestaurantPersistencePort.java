package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel findRestaurantById(Long id);

    List<RestaurantModel> getAllRestaurants();

    List<RestaurantModel> getRestaurantsWithPagination(int page, int size);
}
