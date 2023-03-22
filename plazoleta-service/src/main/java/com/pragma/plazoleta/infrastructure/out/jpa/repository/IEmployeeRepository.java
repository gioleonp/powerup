package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, EmployeeId> {
}
