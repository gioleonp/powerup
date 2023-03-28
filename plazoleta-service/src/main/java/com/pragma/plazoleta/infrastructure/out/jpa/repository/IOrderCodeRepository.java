package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.OrderCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderCodeRepository extends JpaRepository<OrderCodeEntity, String> {}
