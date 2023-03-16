package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(
                restaurantEntityMapper.toEntity(restaurantModel));

        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        if (restaurantEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }
}
