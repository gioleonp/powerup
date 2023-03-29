package com.pragma.plazoleta.application.dto.response;

import com.pragma.plazoleta.domain.model.EOrderState;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long idCliente;
    private LocalDateTime fecha;
    private EOrderState estado;
    private Long idChef;
    private List<OrderDishResponseDto> orderDishes;
    private RestaurantResponseDto restaurante;
}
