package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.application.dto.response.UserResponseDto;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;

import java.util.List;
import java.util.regex.Matcher;
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

        // Verify name constains at least one letter
        Pattern namePattern = Pattern.compile("[a-zA-Zá-úÁ-Ú]+");
        Matcher nameMatcher = namePattern.matcher(restaurantModel.getNombre());

        String phonePattern = "\\+?\\d{12}";
        String nitPattern = "^\\d+$";

        UserResponseDto user = userServiceCommunicationPort.findUser(
                restaurantModel.getIdPropietario());

        if (!user.getRol().getName().equals("ROLE_PROPIETARIO")) {
            throw new DomainException("USER NOT AUTHORIZED");
        } else if (restaurantModel.getDireccion().strip().length() == 0) {
            throw new DomainException("DIRECCION ERRONEA: " + restaurantModel.getDireccion());
        }else if (restaurantModel.getUrlLogo().strip().length() == 0) {
            throw new DomainException("URL LOGO INVALIDA");
        } else if (!nameMatcher.find()){
            throw new DomainException("NOMBRE INCORRECTO: " + restaurantModel.getNombre());
        } else if (!restaurantModel.getTelefono().matches(phonePattern)) {
            throw new DomainException("NUMERO INVALIDO: " + restaurantModel.getTelefono());
        } else if (!restaurantModel.getNit().matches(nitPattern)){
            throw new DomainException("NIT INVALIDO: " + restaurantModel.getNit());
        }

        restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }
}
