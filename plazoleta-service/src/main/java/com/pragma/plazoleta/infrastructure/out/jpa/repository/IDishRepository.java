package com.pragma.plazoleta.infrastructure.out.jpa.repository;

import com.pragma.plazoleta.domain.model.DishModel;
import com.pragma.plazoleta.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {

    @Query(
            value =
                    "SELECT * FROM platos p "
                            + "INNER JOIN restaurantes_platos rp "
                            + "ON p.id = rp.id_plato "
                            + "INNER JOIN categorias_platos cp "
                            + "ON cp.id_plato = p.id "
                            + "INNER JOIN categorias c "
                            + "ON c.id = cp.id_categoria "
                            + "INNER JOIN restaurantes r "
                            + "ON r.id = rp.id_restaurante "
                            + "WHERE rp.id_restaurante = :idRestaurante "
                            + "ORDER BY c.nombre ASC",
            countQuery =
                    "SELECT COUNT(*) FROM platos p "
                            + "INNER JOIN restaurantes_platos rp "
                            + "ON p.id = rp.id_plato "
                            + "INNER JOIN categorias_platos cp "
                            + "ON cp.id_plato = p.id "
                            + "INNER JOIN categorias c "
                            + "ON c.id = cp.id_categoria "
                            + "INNER JOIN restaurantes r "
                            + "ON r.id = rp.id_restaurante "
                            + "WHERE rp.id_restaurante = :idRestaurante "
                            + "ORDER BY C.nombre ASC",
            nativeQuery = true)
    Page<DishEntity> findDishByRestaurantWithPaginationByCategory(
            @Param("idRestaurante") Long idRestaurante, Pageable pageable);
}
