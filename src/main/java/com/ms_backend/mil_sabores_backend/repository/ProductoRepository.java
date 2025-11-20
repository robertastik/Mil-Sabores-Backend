package com.ms_backend.mil_sabores_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ms_backend.mil_sabores_backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
