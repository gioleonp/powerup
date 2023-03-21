package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IRestaurantServicePort restaurantServicePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort,
                       IUserServiceCommunicationPort userServiceCommunicationPort,
                       IRestaurantServicePort restaurantServicePort){
        this.dishPersistencePort = dishPersistencePort;
        this.userServiceCommunicationPort = userServiceCommunicationPort;
        this.restaurantServicePort = restaurantServicePort;
    }

    @Override
    public void saveDish(Long id_proprietary, DishModel dishModel) {
        // Check if restaurant is not null
        if (dishModel.getRestaurante() == null){
            throw new DomainException("RESTAURANTE ES UN ATRIBUTO OBLIGATORIO");
        }

        // If a restaurant was provided retrieve it from database
        RestaurantModel restaurantModel = restaurantServicePort.findRestaurantById(
                dishModel.getRestaurante().getId());

        /*
          Check if the idProprietary of the restaurant match with the id
          with the id of who is making the petition
        */
        if (restaurantModel.getIdPropietario() != id_proprietary) {
            throw new ProprietaryNotMatchException();
        }

        // Check validity of the fields
        if (dishModel.getNombre() == null
        || dishModel.getNombre().strip().length() == 0){
            throw new DomainException("NOMBRE ES UN ATRIBUTO OBLIGATORIO");
        } else if (dishModel.getDescripcion() == null
        || dishModel.getDescripcion().strip().length() == 0){
            throw new DomainException("DESCRIPCION ES UN ATRIBUTO OBLIGATORIO");
        } else if (dishModel.getUrlImagen() == null
        || dishModel.getUrlImagen().strip().length() == 0){
            throw new DomainException("URL IMAGEN  ES UN ATRIBUTO OBLIGATORIO");
        } else if (dishModel.getPrecio() <= 0) {
            throw new DomainException("PRECIO DEBE SER UN NUMERO ENTERO POSITIVO MAYOR A CERO");
        } else if (dishModel.getCategoria() == null) {
            throw new DomainException("CATEGORIA ES UN ATRIBUTO OBLIGATORIO");
        } else if (dishModel.getRestaurante() == null)  {
            throw new DomainException("RESTAURANTE ES UN ATRIBUTO OBLIGATORIO");
        }

        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel findDishById(int id) {
        return dishPersistencePort.findDishById(id);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void updateDish(Long id_proprietary, int id_dish, DishModel dishModel) {
        // check it the dish already exists.
        DishModel foundDish = dishPersistencePort.findDishById(id_dish);

        /*
          Validating if there's an intention of modify another field
          besides of those available.
        */
         if (dishModel.getDescripcion() == null
                || dishModel.getDescripcion().strip().length() == 0) {

            throw new DomainException("LA NUEVA DESCRIPCION NO PUEDE SER VACIA");

        } else if (dishModel.getPrecio() <= 0) {
             throw new DomainException("EL NUEVO PRECIO NO PUEDE SER MENOR O IGUAL A CERO");
         } else if (dishModel.getCategoria() != null) {
            throw new DomainException("ATRIBUTO CATEGORIA NO PUEDE SER ACTUALIZADO");
        } else if (dishModel.getUrlImagen() != null) {
            throw new DomainException("ATRIBUTO URL IMAGEN NO PUEDE SER ACTUALIZADO");
        } else if (dishModel.getNombre() != null) {
            throw new DomainException("ATRIBUTO NOMBRE NO PUEDE SER ACTUALIZADO");
        } else if (dishModel.getActivo() != null) {
            throw new DomainException("ATRIBUTO ACTIVO NO PUEDE SER ACTUALIZADO");
        }  else if (dishModel.getRestaurante() != null) {
            throw new DomainException("ATRIBUTO RESTAURANTE NO PUEDE SER ACTUALIZADO");
        }


        // Verify if the one making the request is the actual proprietary of the restaurant
        UserModel proprietary = userServiceCommunicationPort.findUserById(id_proprietary);

        if (proprietary.getId() != foundDish.getRestaurante().getIdPropietario())  {
            throw new ProprietaryNotMatchException();
        }

        // Assign new price and description to the dish
        foundDish.setPrecio(dishModel.getPrecio());
        foundDish.setDescripcion(dishModel.getDescripcion());

        dishPersistencePort.updateDish(foundDish);
    }
}