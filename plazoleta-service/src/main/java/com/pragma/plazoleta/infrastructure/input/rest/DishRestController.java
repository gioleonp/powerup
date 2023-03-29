package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.DishRequestDto;
import com.pragma.plazoleta.application.dto.request.DishRequestPriceAndDescriptionDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/plato")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Get all dishes")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All dishes found",
                        content = @Content),
                @ApiResponse(
                        responseCode = "404",
                        description = "No data found",
                        content = @Content)
            })
    @GetMapping("")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @Operation(summary = "Update dish")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Dish Updated", content = @Content)
            })
    @PostMapping("/update/{id_dish}")
    public ResponseEntity<Void> updateDish(
            @Valid @RequestBody DishRequestPriceAndDescriptionDto dishRequestDto,
            @RequestParam("proprietary") long idProprietary,
            @PathVariable("id_dish") Long idDish) {
        dishHandler.updateDish(idProprietary, idDish, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update dish state")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Dish state updated successfully",
                        content = @Content),
            })
    @PostMapping("/updateActive/{id_dish}")
    public ResponseEntity<DishResponseDto> updateActive(
            @RequestParam("active") boolean active,
            @RequestParam("proprietary") long idProprietary,
            @PathVariable("id_dish") Long idDish) {
        return ResponseEntity.ok(dishHandler.updateActive(active, idProprietary, idDish));
    }

    @Operation(summary = "Save dish")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "Dishes saved", content = @Content)
            })
    @PostMapping("")
    public ResponseEntity<Void> saveDish(
            @Valid @RequestBody DishRequestDto dishRequestDto,
            @RequestParam("proprietary") long idProprietary) {
        dishHandler.saveDish(idProprietary, dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get dish from a restaurant")
    @ApiResponses(
            value = {@ApiResponse(responseCode = "200", description = "Menu", content = @Content)})
    @GetMapping("/menu/{id_restaurante}")
    public ResponseEntity<List<DishResponseDto>> getDishesByRestaurantWithPaginationByCategory(
            @PathVariable("id_restaurante") long idRestaurante,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        return ResponseEntity.ok(
                dishHandler.getDishesByRestaurantWithPaginationByCategory(
                        idRestaurante, page, size));
    }
}
