package com.pragma.usermicroservice.infrastructure.out.jpa.repository;

import com.pragma.usermicroservice.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RoleEntity, Long> {

}