package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import com.pragma.plazoleta.domain.exception.SameStateException;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantServicePort restaurantServicePort;

    public DishUseCase(
            IDishPersistencePort dishPersistencePort,
            IRestaurantServicePort restaurantServicePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantServicePort = restaurantServicePort;
    }

    @Override
    public void saveDish(Long idProprietary, DishModel dishModel) {
        // If a restaurant was provided retrieve it from database
        RestaurantModel restaurantModel =
                restaurantServicePort.findRestaurantById(dishModel.getRestaurante().getId());

        /*
          Check if the idProprietary of the restaurant match with the id
          with the id of who is making the petition
        */
        if (restaurantModel.getIdPropietario() != idProprietary) {
            throw new ProprietaryNotMatchException();
        }

        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel findDishById(Long id) {
        return dishPersistencePort.findDishById(id);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void updateDish(Long idProprietary, Long idDish, DishModel dishModel) {
        // check it the dish already exists.
        DishModel foundDish = dishPersistencePort.findDishById(idDish);

        // Verify if the one making the request is the actual proprietary of the restaurant
        if (idProprietary != foundDish.getRestaurante().getIdPropietario()) {
            throw new ProprietaryNotMatchException();
        }

        // Assign new price and description to the dish
        foundDish.setPrecio(dishModel.getPrecio());
        foundDish.setDescripcion(dishModel.getDescripcion());

        dishPersistencePort.updateDish(foundDish);
    }

    @Override
    public DishModel updateActive(boolean active, Long idProprietary, Long idDish) {

        DishModel dishModel = dishPersistencePort.findDishById(idDish);
        if (idProprietary != dishModel.getRestaurante().getIdPropietario()) {
            throw new ProprietaryNotMatchException();
        } else if (dishModel.getActivo() == active) {
            throw new SameStateException();
        }

        dishModel.setActivo(active);
        return dishPersistencePort.updateActive(dishModel);
    }

    @Override
    public List<DishModel> getDishesByRestaurantWithPaginationByCategory(
            Long idRestaurant, int page, int size) {
        return dishPersistencePort.getDishesByRestaurantWithPaginationByCategory(
                idRestaurant, page, size);
    }
}
