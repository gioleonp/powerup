package com.pragma.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private Long id;
    private Long idCliente;
    private LocalDateTime fecha;
    private EOrderState estado;
    private Long idChef;
    private List<OrderDishModel> orderDishes;
    private RestaurantModel restaurante;
}
