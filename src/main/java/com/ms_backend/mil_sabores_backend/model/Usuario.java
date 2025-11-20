package com.ms_backend.mil_sabores_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String email;
    private String fechaNacimiento;
    private String password;
    private Integer edad;
    private Boolean isDuoc;
    private Boolean felicesCincuenta;
    private String preferencias;
}
