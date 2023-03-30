package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query(value = "SELECT r FROM RestaurantEntity r order by r.nombre asc")
    Page<RestaurantEntity> findAllRestaurantsWithPagination(Pageable pageable);
}
