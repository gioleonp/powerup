package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(RestaurantModel restaurantModel);
    RestaurantModel findRestaurantById(Long id);
    List<RestaurantModel> getAllRestaurants();
}
