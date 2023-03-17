package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.domain.spi.persistence.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort){
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(DishModel dishModel) {

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
            throw new DomainException("CATEGORIA ES UN CAMPO OBLIGATORIO");
        } else if (dishModel.getRestaurante() == null)  {
            throw new DomainException("RESTAURANTE ES UN VALOR OBLIGATORIO");
        }

        dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public List<DishModel> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }
}
