package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderDishId;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity, OrderDishId> {

    List<OrderDishEntity> findAllByIdPedido(Long idPedido);

}
