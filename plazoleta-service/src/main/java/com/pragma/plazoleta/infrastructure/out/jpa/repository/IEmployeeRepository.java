package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.EmployeeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, EmployeeId> {

    // @Query(value = "SELECT * FROM employees WHERE id_usuario = :empleado", nativeQuery = true)
    EmployeeEntity findByIdUsuario(@Param("empleado") Long idEmployee);
}
