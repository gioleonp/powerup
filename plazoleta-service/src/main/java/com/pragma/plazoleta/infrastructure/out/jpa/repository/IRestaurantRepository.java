package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.RestaurantNameAndUrlEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query(
            value =
                    "SELECT new com.pragma.plazoleta.infrastructure.out.jpa.entity"
                            + ".RestaurantNameAndUrlEntity(r.nombre, r.urlLogo) FROM RestaurantEntity r")
    Page<RestaurantNameAndUrlEntity> findAllRestaurantsWithPagination(Pageable pageable);
}
