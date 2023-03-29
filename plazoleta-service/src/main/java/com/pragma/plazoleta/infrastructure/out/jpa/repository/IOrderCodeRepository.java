package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;

public interface IOrderCodeRepository extends JpaRepository<OrderCodeEntity, String> {
    Optional<OrderCodeEntity> findOrderCodeByIdOrder(Long idOrder);
}
