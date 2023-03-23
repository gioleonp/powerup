package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;import com.pragma.plazoleta.domain.model.RestaurantModel;
import com.pragma.plazoleta.domain.model.RestaurantNameAndUrlModel;import com.pragma.plazoleta.domain.model.UserModel;
import com.pragma.plazoleta.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;

import java.util.List;import java.util.regex.Pattern;

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

        String correctedPhoneNumber = restaurantModel.getTelefono().contains("+")
                ? restaurantModel.getTelefono()
                : "+" + restaurantModel.getTelefono();

        restaurantModel.setTelefono(correctedPhoneNumber);

        // If there's no missing data, retrieve user from user service
        UserModel user = userServiceCommunicationPort.findUserById(
                restaurantModel.getIdPropietario());

        // Checking the validity and authorization
        if (!user.getRol().getName().equals("ROLE_PROPIETARIO")) {
            throw new ProprietaryNotMatchException();
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

    @Override
    public List<RestaurantNameAndUrlModel> getRestaurantsWithPagination(int page, int size) {
        return restaurantPersistencePort.getRestaurantsWithPagination(page, size);
    }
}
