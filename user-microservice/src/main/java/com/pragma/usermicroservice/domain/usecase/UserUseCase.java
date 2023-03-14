package com.pragma.usermicroservice.domain.usecase;

import com.pragma.usermicroservice.domain.api.IUserServicePort;
import com.pragma.usermicroservice.domain.exception.DomainException;
import com.pragma.usermicroservice.domain.model.UserModel;
import com.pragma.usermicroservice.domain.spi.IPasswordEncoder;
import com.pragma.usermicroservice.domain.spi.IUserPersistencePort;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoder passwordEncoder;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserModel userModel) {

        // Patterns
        Pattern phoneNumberPattern = Pattern.compile("\\+\\d{13}");
        Pattern emailPattern = Pattern.compile("\\d{10}");
        Pattern documentPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}");

        // Validation
        Matcher phoneMatcher = phoneNumberPattern.matcher(userModel.getPhoneNumber());
        Matcher emailMatcher = emailPattern.matcher(userModel.getEmail());
        Matcher documentMatcher = documentPattern.matcher(userModel.getIdentificationDocument());


        if(userModel.getName().strip().length() == 0){
            throw new DomainException("NOMBRE ES UN ATRIBUTO OBLIGATORIO");
        } else if (userModel.getLastName().strip().length() == 0) {
            throw new DomainException("APELLIDO ES UN ATRIBUTO OBLIGATORIO");
        }else if (!phoneMatcher.matches()){
           throw new DomainException("NÚMERO NO VALIDO: " + userModel.getPhoneNumber());
        } else if (!emailMatcher.matches()) {
            throw new DomainException("EMAIL NO VALIDO: " + userModel.getEmail());
        } else if (!documentMatcher.matches()) {
            throw new DomainException("DOCUMENTO DE IDENTIDAD INVALIDO: "
                    + userModel.getIdentificationDocument());
        }

        // Giving format to the phoneNumber
        String correctedPhoneNumber = userModel.getPhoneNumber().contains("+")
        ? userModel.getPhoneNumber()
        : "+" + userModel.getPhoneNumber();

        userModel.setPhoneNumber(correctedPhoneNumber);

        // Encrypting
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));


        userPersistencePort.saveUser(userModel);
    }

    @Override
    public UserModel findByEmail(String email) {
        return this.userPersistencePort.findByEmail(email);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }
}