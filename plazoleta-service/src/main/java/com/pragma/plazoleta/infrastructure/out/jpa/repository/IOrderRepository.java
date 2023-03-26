package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.EOrderState;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(
            value =
                    "SELECT COUNT(*) FROM pedidos "
                            + "WHERE estado = 'PENDIENTE' "
                            + "OR estado = 'EN_PREPARACION' "
                            + "OR estado = 'LISTO' "
                            + "AND id_cliente = :cliente",
            nativeQuery = true)
    int getNumberOfOrdersWithStateInPreparationPendingOrReady(@Param("cliente") Long idClient);

    @Query(
            value =
                    "SELECT * FROM pedidos "
                            + "WHERE estado = :estado "
                            + "AND id_restaurante = :restaurante",
            countQuery =
                    "SELECT COUNT(*) FROM pedidos "
                            + "WHERE estado = :estado "
                            + "AND id_restaurante = :restaurante",
            nativeQuery = true)
    Page<OrderEntity> findAllOrderByStateAndRestaurant(
            @Param("estado") String orderState,
            @Param("restaurante") Long idRestaurante,
            Pageable pageable);
}
