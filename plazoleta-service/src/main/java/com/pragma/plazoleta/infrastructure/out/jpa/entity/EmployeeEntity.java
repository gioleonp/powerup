package com.pragma.plazoleta.infrastructure.out.jpa.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "restaurantes_empleados")
@IdClass(EmployeeId.class)
public class EmployeeEntity {
    @Id
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Id
    @Column(name = "id_restaurante", nullable = false)
    private Long idRestaurante;


}
