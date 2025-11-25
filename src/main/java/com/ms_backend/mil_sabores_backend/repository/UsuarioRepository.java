package com.ms_backend.mil_sabores_backend.repository;

import com.ms_backend.mil_sabores_backend.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
