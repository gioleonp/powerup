package com.pragma.usermicroservice.infrastructure.out.jpa.entity;

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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_user", nullable = false)
    private Long id_user;

    @Column(name = "id", nullable = false, unique = true)
    private String identificationDocument;

    @Column(length = 70, nullable = false)
    private String name;
    @Column(length = 70, nullable = false)
    private String lastName;

    @Column(length = 13, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;

    @ManyToOne()
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "id_user", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "id_role", nullable = false)
    )
    private RoleEntity role;
}
