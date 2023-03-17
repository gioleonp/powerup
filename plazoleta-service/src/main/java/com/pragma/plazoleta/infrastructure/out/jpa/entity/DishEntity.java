package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "platos")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;
    @ManyToOne(optional = false)
    @JoinTable(name = "categorias_platos",
            joinColumns = @JoinColumn(name = "id_plato", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", nullable = false))
    private CategoryEntity categoria;

    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private int precio;
    @ManyToOne(optional = false)
    @JoinTable(name = "restaurantes_platos",
            joinColumns = @JoinColumn(name = "id_plato", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_restaurante", nullable = false))
    private RestaurantEntity restaurante;
    private String urlImagen;
    private Boolean activo;
}
