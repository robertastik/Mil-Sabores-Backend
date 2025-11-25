package com.ms_backend.mil_sabores_backend.service;

import com.ms_backend.mil_sabores_backend.model.Usuario;
import com.ms_backend.mil_sabores_backend.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    public List<Usuario> crearUsuario(List<Usuario> usuarios) {
        return usuarioRepo.saveAll(usuarios);
    }

    public Usuario crearUsuario(Usuario usuario) {
        String passEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passEncriptada);
        
        return usuarioRepo.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioExistente.setEdad(usuario.getEdad());
            usuarioExistente.setIsDuoc(usuario.getIsDuoc());
            usuarioExistente.setFelicesCincuenta(usuario.getFelicesCincuenta());
            usuarioExistente.setPreferencias(usuario.getPreferencias());

            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                 String passEncriptada = passwordEncoder.encode(usuario.getPassword());
                 usuarioExistente.setPassword(passEncriptada);
            }
            return usuarioRepo.save(usuarioExistente);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
}
