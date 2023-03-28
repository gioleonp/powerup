package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.RolModel;
import com.pragma.plazoleta.domain.model.UserModel;

public class UserDataTest {

    public static UserModel getUserModel() {
        UserModel userModel =
                new UserModel(
                        1L,
                        "1002192504",
                        "giovanni",
                        "leon",
                        "+573058388527",
                        "giovanni@gmail.com",
                        "12345",
                        new RolModel());

        return userModel;
    }
}
