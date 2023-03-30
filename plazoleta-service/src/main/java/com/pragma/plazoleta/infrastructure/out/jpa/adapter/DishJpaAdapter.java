package com.pragma.plazoleta.infrastructure.out.jpa.adapter;

import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel saveDish(DishModel dish) {
        DishEntity dishEntity = dishRepository.save(dishEntityMapper.toEntity(dish));
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public DishModel findDishById(Long id) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        if (dishEntity.isEmpty()) {
            throw new NoDataFoundException("DISH");
        }
        return dishEntityMapper.toDishModel(dishEntity.get());
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishEntities = dishRepository.findAll();
        if (dishEntities.isEmpty()) {
            throw new NoDataFoundException("DISHES");
        }
        return dishEntityMapper.toDishModelList(dishEntities);
    }

    @Override
    public DishModel updateDish(DishModel dishModel) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dishModel);
        return dishEntityMapper.toDishModel(dishRepository.save(dishEntity));
    }

    @Override
    public DishModel updateActive(DishModel dishModel) {
        DishEntity dish = dishRepository.save(dishEntityMapper.toEntity(dishModel));
        return dishEntityMapper.toDishModel(dish);
    }

    @Override
    public List<DishModel> getDishesByRestaurantWithPaginationByCategory(
            Long idRestaurant, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<DishEntity> listDish =
                dishRepository
                        .findDishByRestaurantWithPaginationByCategory(idRestaurant, pageable)
                        .toList();

        return dishEntityMapper.toDishModelList(listDish);
    }
}
