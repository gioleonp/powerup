package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;
import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(RestaurantModel restaurantModel);
    RestaurantModel findRestaurantById(Long id);
    List<RestaurantModel> getAllRestaurants();

    List<RestaurantNameAndUrlModel> getRestaurantsWithPagination(int page, int size);
}
