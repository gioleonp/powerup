package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.infrastructure.exception.ProprietaryNotMatchException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;
    private final IRestaurantHandler restaurantHandler;

    @PostMapping("/{id_proprietary}")
    public ResponseEntity<Void> saveDish(@RequestBody DishRequestDto dishRequestDto,
    @PathVariable("id_proprietary") long id_proprietary){

        // Check if restaurant is not null
        if (dishRequestDto.getRestaurante() == null){
            throw new DomainException("RESTAURANTE ES UN ATRIBUTO OBLIGATORIO");
        }

        // If a restaurant was provided retrieve it from database
        RestaurantResponseDto restaurantResponseDto = restaurantHandler.findRestaurantById(
                dishRequestDto.getRestaurante().getId());


        /*
          Check if the idProprietary of the restaurant match with the id
          with the id of who is making the petition
        */
        if (restaurantResponseDto.getIdPropietario() != id_proprietary) {
            throw new ProprietaryNotMatchException();
        }

        dishHandler.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(){
        return  ResponseEntity.ok(dishHandler.getAllDishes());
    }


}
