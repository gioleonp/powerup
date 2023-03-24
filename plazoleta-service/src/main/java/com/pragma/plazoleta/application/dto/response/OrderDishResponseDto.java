package com.pragma.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDishResponseDto {

    private Long idRestuarante;
    private int idPlato;
    private int cantidad;
}
