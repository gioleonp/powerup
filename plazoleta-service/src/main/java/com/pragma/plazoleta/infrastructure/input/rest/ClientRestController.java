package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.request.UserRequestDto;
import com.pragma.plazoleta.application.dto.response.DishResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoleta.application.dto.response.RestaurantResponseNameAndUrlDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.domain.spi.servicecommunication.IUserServiceCommunicationPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.build.Plugin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ClientRestController {

    private final IUserServiceCommunicationPort userServiceCommunicationPort;
    private final IRestaurantHandler restaurantHandler;
    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new client")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Client created",
                        content = @Content),
                @ApiResponse(
                        responseCode = "409",
                        description = "Client already exists",
                        content = @Content)
            })
    @PostMapping("/register")
    public ResponseEntity<Void> createClient(@RequestBody UserRequestDto userDto) {
        userServiceCommunicationPort.createClient(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cliente/restaurantes")
    public ResponseEntity<List<RestaurantResponseNameAndUrlDto>> getRestaurantsWithPagination(
            @RequestParam("page") int page, @RequestParam("size") int size) {

        return ResponseEntity.ok(restaurantHandler.getRestaurantsWithPagination(page, size));
    }

    @GetMapping("/cliente/restaurante/{id_restaurante}")
    public ResponseEntity<List<DishResponseDto>> getMenu(
            @PathVariable("id_restaurante") Long idRestaurante,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {

        List<DishResponseDto> dishResponseDtos =
                dishHandler.getDishesByRestaurantWithPaginationByCategory(
                        idRestaurante, page, size);

        return ResponseEntity.ok(dishResponseDtos);
    }
}
