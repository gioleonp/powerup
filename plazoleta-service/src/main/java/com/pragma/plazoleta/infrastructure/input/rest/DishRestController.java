package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.DishRequestPriceAndDescriptionDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;
    private final IRestaurantHandler restaurantHandler;

    @PostMapping("")
    public ResponseEntity<Void> saveDish(@RequestBody DishRequestPriceAndDescriptionDto dishRequestDto,
                                         @RequestParam("proprietary") long idProprietary) {
        dishHandler.saveDish(idProprietary, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(){
        return  ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @PostMapping("/update/{id_dish}")
    public ResponseEntity<Void> updateDish(
            @Valid @RequestBody DishRequestPriceAndDescriptionDto dishRequestDto,
            @RequestParam("proprietary") long idProprietary,
            @PathVariable("id_dish") int idDish){
        dishHandler.updateDish(idProprietary, idDish, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/updateActive")
    public ResponseEntity<DishResponseDto> updateActive(boolean active,
                                  @RequestParam("proprietary") long idProprietary,
                                  @RequestParam("dish") int idDish){
        return ResponseEntity.ok(dishHandler.updateActive(active, idProprietary, idDish));
    }
}
