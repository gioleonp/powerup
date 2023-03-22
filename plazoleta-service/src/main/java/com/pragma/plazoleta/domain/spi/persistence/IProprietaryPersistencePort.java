package com.pragma.plazoleta.domain.spi.persistence;

import com.pragma.plazoleta.domain.model.UserModel;

public interface IProprietaryPersistencePort {

    UserModel saveEmployee(UserModel userModel);
}
