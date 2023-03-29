package com.pragma.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDishRequestDto {

    @NotNull(message = "PLATO ES UN ATRIBUTO OBLIGATORIO")
    @Range(min = 1, message = "ID PLATO ES UN NUMERO ENTERO Y MAYOR DE CERO")
    private Long idPlato;

    @NotNull(message = "CANTIDAD ES UN ATRIBUTO OBLIGATORIO")
    @Range(min = 1, message = "CANTIDAD ES UN NUMERO ENTERO Y MAYOR DE CERO")
    private int cantidad;
}
