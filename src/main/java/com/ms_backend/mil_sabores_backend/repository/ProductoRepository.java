package com.ms_backend.mil_sabores_backend.repository;

import com.ms_backend.mil_sabores_backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
