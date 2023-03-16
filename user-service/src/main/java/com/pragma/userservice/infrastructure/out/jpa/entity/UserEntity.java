package com.pragma.userservice.infrastructure.out.jpa.entity;

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

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "documento_de_identidad", nullable = false, unique = true)
    private String documentoDeIdentidad;

    @Column(length = 70, nullable = false)
    private String nombre;
    @Column(length = 70, nullable = false)
    private String apellido;

    @Column(length = 13, nullable = false)
    private String celular;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String contrasenia;

    @ManyToOne(optional = false)
    @JoinTable(name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "id_usuario", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "id_role", nullable = false)
    )
    private RoleEntity rol;
}
