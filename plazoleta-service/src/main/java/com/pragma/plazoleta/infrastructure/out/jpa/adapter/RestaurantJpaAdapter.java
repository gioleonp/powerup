package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity =
                restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));

        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if (restaurantEntity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity.get());
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        if (restaurantEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }

    @Override
    public List<RestaurantModel> getRestaurantsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        List<RestaurantEntity> restaurantEntityList =
                restaurantRepository.findAllRestaurantsWithPagination(pageable).toList();

        return restaurantEntityMapper.toRestaurantModelList(restaurantEntityList);
    }
}
