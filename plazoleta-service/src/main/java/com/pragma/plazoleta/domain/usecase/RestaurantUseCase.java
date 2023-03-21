package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;

import java.util.List;
import java.util.regex.Pattern;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserServiceCommunicationPort userServiceCommunicationPort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort,
                             IUserServiceCommunicationPort userServiceCommunicationPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userServiceCommunicationPort = userServiceCommunicationPort;
    }

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {

        // Patterns
        Pattern namePattern = Pattern.compile("[a-zA-Zá-úÁ-Ú]+");
        String phonePattern = "\\+?\\d{12}";
        String nitPattern = "^\\d+$";

        // Checking for the presence of null values
        if (restaurantModel.getIdPropietario() == 0) {
            throw new DomainException("ID PROPIETARIO ES UN ATRIBUTO OBLIGATORIO");
        } else if (restaurantModel.getDireccion() == null
        || restaurantModel.getDireccion().strip().length() == 0) {

            throw new DomainException("DIRECCION ES UN ATRIBUTO OBLIGATORIO ");

        }else if (restaurantModel.getUrlLogo() == null
        || restaurantModel.getUrlLogo().strip().length() == 0) {

            throw new DomainException("URL LOGO ES UN ATRIBUTO OBLIGATORIO");

        } else if (restaurantModel.getNombre() == null
        || restaurantModel.getNombre().strip().length() == 0){

            throw new DomainException("NOMBRE ES UN ATRIBUTO OBLIGATORIO ");

        } else if (restaurantModel.getTelefono() == null
        || restaurantModel.getTelefono().strip().length() == 0) {

            throw new DomainException("NUMERO ES UN ATRIBUTO OBLIGATORIO");

        } else if (restaurantModel.getNit() == null
        || restaurantModel.getNit().strip().length() == 0){

            throw new DomainException("NIT ES UN ATRIBUTO OBLIGATORIO");
        }

        // If there's no missing data, retrieve user from user service
        UserModel user = userServiceCommunicationPort.findUserById(
                restaurantModel.getIdPropietario());

        // Checking the validity and authorization
        if (!user.getRol().getName().equals("ROLE_PROPIETARIO")) {
            throw new DomainException("USER NOT AUTHORIZED");
        }  else if (!namePattern.matcher(restaurantModel.getNombre()).find()){
            throw new DomainException("NOMBRE INCORRECTO: " + restaurantModel.getNombre());
        }  else if (!restaurantModel.getTelefono().matches(phonePattern)) {
            throw new DomainException("NUMERO INVALIDO: " + restaurantModel.getTelefono());
        } else if (!restaurantModel.getNit().matches(nitPattern)){
            throw new DomainException("NIT INVALIDO: " + restaurantModel.getNit());
        }

        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public RestaurantModel findRestaurantById(Long id) {
        return restaurantPersistencePort.findRestaurantById(id);
    }


    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}
