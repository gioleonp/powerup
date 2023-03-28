package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "order_code")
public class OrderCodeEntity {

    @Id private String code;

    @Column(name = "id_order")
    private Long idOrder;
}
