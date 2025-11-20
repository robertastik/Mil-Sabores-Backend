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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prod;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String categoria;
    private String descripcion;
    private Double precio;
    private String ingredientes;
    private String imagenUrl;
}
