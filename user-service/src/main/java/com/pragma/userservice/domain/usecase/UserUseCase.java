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
        Pattern phoneNumberPattern = Pattern.compile("\\+?\\d{12}");
        Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}");
        Pattern documentPattern = Pattern.compile("\\d{10}");

        // Validation
        Matcher phoneMatcher = phoneNumberPattern.matcher(userModel.getCelular());
        Matcher emailMatcher = emailPattern.matcher(userModel.getEmail());
        Matcher documentMatcher = documentPattern.matcher(userModel.getDocumentoDeIdentidad());


        if(userModel.getNombre() == null || userModel.getNombre().strip().length() == 0){
            throw new DomainException("NOMBRE ES UN ATRIBUTO OBLIGATORIO");
        } else if (userModel.getApellido() == null || userModel.getApellido().strip().length() == 0) {
            throw new DomainException("APELLIDO ES UN ATRIBUTO OBLIGATORIO");
        }else if (!phoneMatcher.matches()){
           throw new DomainException("NÚMERO NO VALIDO: " + userModel.getCelular());
        } else if (!emailMatcher.matches()) {
            throw new DomainException("EMAIL NO VALIDO: " + userModel.getEmail());
        } else if (!documentMatcher.matches()) {
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

        RoleModel rol = new RoleModel(1, ERoles.ROLE_ADMINISTRADOR, "Administrador de la app");

        userModel.setRol(rol);

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