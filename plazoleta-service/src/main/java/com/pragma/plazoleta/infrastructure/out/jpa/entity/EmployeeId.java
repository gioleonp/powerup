package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeId implements Serializable {
    private Long idUsuario;
    private Long idRestaurante;
}
