package com.pragma.plazoleta.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "restaurantes")
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(name = "id_propietario")
    private long idPropietario;

    @Column(nullable = false, unique = true)
    private String telefono;

    @Column(name = "url_logo", nullable = false)
    private String urlLogo;
    @Column(nullable = false, unique = true)
    private String nit;





}
