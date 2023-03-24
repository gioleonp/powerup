package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @Range(min = 1)
    @NotNull(message = "ID CLIENTE ES UN ATRIBUTO OBLIGATORIO")
    private Long idCliente;

    @NotNull(message = "PLATOS ES UN ATRIBUTO OBLIGATORIO")
    private List<OrderDishRequestDto> platos;

    @NotNull(message = "RESTAURANTE ES UN ATRIBUTO OBLIGATORIO")
    private RestaurantRequestDto restaurante;
}
