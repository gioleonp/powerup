package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(
            value =
                    "SELECT COUNT(*) FROM pedidos "
                            + "WHERE estado = 'PENDIENTE' "
                            + "OR estado = 'EN_PREPARACION' "
                            + "OR estado = 'LISTO' " +
                            "AND id_cliente = :cliente",
            nativeQuery = true)
    int getNumberOfOrdersWithStateInPreparationPendingOrReady(@Param("cliente") Long idClient);
}
