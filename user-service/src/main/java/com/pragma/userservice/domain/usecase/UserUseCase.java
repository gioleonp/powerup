package com.pragma.userservice.domain.usecase;

import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.exception.DomainException;
import com.pragma.userservice.domain.model.ERoles;
import com.pragma.userservice.domain.model.RoleModel;
import com.pragma.userservice.domain.model.UserModel;
import com.pragma.userservice.domain.spi.passwordencoding.IUserPasswordEncoderPort;
import com.pragma.userservice.domain.spi.persistence.IUserPersistencePort;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPasswordEncoderPort passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPasswordEncoderPort passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserModel userModel) {
        // Patterns
        String phoneNumberPattern = "\\+?\\d{12}";
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}";
        String documentPattern = "\\d{10}";


        // Checking for the presence of null values
        if(userModel.getNombre() == null
        || userModel.getNombre().strip().length() == 0){

            throw new DomainException("NOMBRE ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getApellido() == null
        || userModel.getApellido().strip().length() == 0) {

            throw new DomainException("APELLIDO ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getCelular() == null
        || userModel.getCelular().strip().length() == 0){

           throw new DomainException("CELULAR ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getEmail() == null
        || userModel.getEmail().strip().length() == 0) {

            throw new DomainException("EMAIL ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getDocumentoDeIdentidad() == null
        || userModel.getDocumentoDeIdentidad().strip().length() == 0) {

            throw new DomainException("DOCUMENTO DE IDENTIDAD ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getContrasenia() == null
        || userModel.getContrasenia().strip().length() == 0) {

            throw new DomainException("CONTRASENIA ES UN ATRIBUTO OBLIGATORIO");

        } else if (userModel.getRol() == null) {
            throw new DomainException("ROL ES UN CAMPO OBLIGATORIO");
        }

        // Checking validity of the fields
        if (!userModel.getCelular().matches(phoneNumberPattern)) {
            throw new DomainException("CELULAR NO VALIDO: " + userModel.getCelular());
        } else if (!userModel.getEmail().matches(emailPattern)) {
            throw new DomainException("EMAIL NO VALIDO: " + userModel.getEmail());
        } else if (!userModel.getEmail().matches(emailPattern)) {
            throw new DomainException("DOCUMENTO DE IDENTIDAD INVALIDO: "
                    + userModel.getDocumentoDeIdentidad());
        }

        // Giving format to the phoneNumber
        String correctedPhoneNumber = userModel.getCelular().contains("+")
        ? userModel.getCelular()
        : "+" + userModel.getCelular();

        userModel.setCelular(correctedPhoneNumber);

        // Encrypting
        userModel.setContrasenia(passwordEncoder.encode(userModel.getContrasenia()));

        userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return userPersistencePort.findUserByEmail(email);
    }

    @Override
    public UserModel findUserById(Long id) {
        return userPersistencePort.findUserById(id);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}