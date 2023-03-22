package com.pragma.plazoleta.application.handler.impl;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.handler.IProprietaryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional

public class ProprietaryHandlerImpl implements IProprietaryHandler {


    @Override
    public void saveEmployee(UserRequestDto userRequestDto,
                             Long idProprietary,
                             Long idRestaurant){

    }
}
