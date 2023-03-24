package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos_platos")
@Setter
@Getter
@IdClass(OrderDishId.class)
public class OrderDishEntity {

    @Id
    @Column(name = "id_pedido")
    private Long idPedido;

    @Id
    @Column(name = "id_plato")
    private Long idPlato;

    private int cantidad;
}
