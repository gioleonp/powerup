package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.domain.exception.DomainException;
import com.pragma.plazoleta.domain.exception.ProprietaryNotMatchException;
import lombok.RequiredArgsConstructor;
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
    @PathVariable("id_proprietary") long id_proprietary) {
        dishHandler.saveDish(id_proprietary, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(){
        return  ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @PostMapping("/{id_proprietary}/{id_dish}")
    public ResponseEntity<Void> updateDish(@RequestBody DishRequestDto dishRequestDto,
                                           @PathVariable("id_proprietary") long id_proprietary,
                                           @PathVariable("id_dish") int id_dish){
        dishHandler.updateDish(id_proprietary, id_dish, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
