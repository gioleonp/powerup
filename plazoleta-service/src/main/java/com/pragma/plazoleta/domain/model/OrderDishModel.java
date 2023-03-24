package com.pragma.plazoleta.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishModel {
    private Long idPedido;
    private Long idPlato;
    private int cantidad;
}
