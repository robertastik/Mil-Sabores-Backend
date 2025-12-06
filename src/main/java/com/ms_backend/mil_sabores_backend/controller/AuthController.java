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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(loginRequest.getEmail());
            String role = usuario.getRole().name();
            return ResponseEntity.ok(new AuthResponse(token, loginRequest.getEmail(), role));

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

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(userDetails.getUsername());
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            usuario.setPassword(null);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener perfil: " + e.getMessage());
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorEmail(userDetails.getUsername());
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            Usuario actualizado = usuarioService.actualizarUsuario(usuario.getId_user(), usuarioActualizado);
            actualizado.setPassword(null); 
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar perfil: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> testAuth(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.ok("No authenticated user");
        }
        return ResponseEntity.ok("Authenticated as: " + userDetails.getUsername());
    }
}