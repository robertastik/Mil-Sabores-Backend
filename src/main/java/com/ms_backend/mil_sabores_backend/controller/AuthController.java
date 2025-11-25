package com.ms_backend.mil_sabores_backend.controller;

import com.ms_backend.mil_sabores_backend.dto.AuthResponse;
import com.ms_backend.mil_sabores_backend.dto.LoginRequest;
import com.ms_backend.mil_sabores_backend.model.Usuario;
import com.ms_backend.mil_sabores_backend.service.JwtService;
import com.ms_backend.mil_sabores_backend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );
            String token = jwtService.generateToken(loginRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, loginRequest.getEmail()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Error: Usuario o contraseña incorrectos");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
            nuevoUsuario.setPassword(null);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar: " + e.getMessage());
        }
    }
}