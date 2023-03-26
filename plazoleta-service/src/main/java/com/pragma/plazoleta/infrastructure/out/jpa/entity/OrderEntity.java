package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import com.pragma.plazoleta.domain.model.EOrderState;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Setter
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long idCliente;

    private LocalDateTime fecha;

    @Column(name = "estado")
    @Enumerated(value = EnumType.STRING)
    private EOrderState estado;

    @ManyToOne()
    @JoinColumns({
        @JoinColumn(
                name = "id_chef",
                referencedColumnName = "id_usuario",
                insertable = false,
                updatable = false),
        @JoinColumn(
                name = "id_restaurante",
                referencedColumnName = "id_restaurante",
                insertable = false,
                updatable = false)
    })
    private EmployeeEntity chef;

    @ManyToOne()
    @JoinColumn(name = "id_restaurante")
    private RestaurantEntity restaurante;
}
