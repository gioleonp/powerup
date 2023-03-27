package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Get all restaurants")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All restaurants found",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @Operation(summary = "Find restaurant by id")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Restaurant found",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> findRestaurantById(@PathVariable("id") long id) {
        return ResponseEntity.ok(restaurantHandler.findRestaurantById(id));
    }

    @Operation(summary = "Save restaurant")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Restaurant was successfully saved",
                        content = @Content),
                @ApiResponse(
                        responseCode = "409",
                        description = "Restaurant already exists",
                        content = @Content)
            })
    @PostMapping("")
    public ResponseEntity<Void> saveRestaurant(
            @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get restaurants with paging")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Restaurants with paging",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
    @GetMapping("/paging")
    public ResponseEntity<List<RestaurantResponseNameAndUrlDto>> getRestaurantsWithPagination(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        return ResponseEntity.ok(restaurantHandler.getRestaurantsWithPagination(page, size));
    }
}
