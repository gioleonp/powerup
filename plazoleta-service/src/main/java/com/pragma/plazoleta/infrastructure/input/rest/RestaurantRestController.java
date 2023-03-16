package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(){
      return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @PostMapping("")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto){
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
