package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.RestaurantModel;import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel findRestaurantById(Long id);

    List<RestaurantModel> getAllRestaurants();

    List<RestaurantNameAndUrlModel> getRestaurantsWithPagination(int page, int size);
}
