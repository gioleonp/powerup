package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.model.UserModel;public class UserUseCaseDataTest {

    public static UserModel getUserModel(){
        UserModel expectedUser =
                new UserModel(
                        1L,
                        "1002192504",
                        "chuty",
                        "bnet",
                        "573004586742",
                        "chuty@example.com",
                        "password",
                        null);

        return expectedUser;
    }
}
