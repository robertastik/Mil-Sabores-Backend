package com.ms_backend.mil_sabores_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ms_backend.mil_sabores_backend.model.Post;
import com.ms_backend.mil_sabores_backend.model.Usuario;
import com.ms_backend.mil_sabores_backend.repository.PostRepository;
import com.ms_backend.mil_sabores_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getFechaCreacion().compareTo(p1.getFechaCreacion()))
                .toList();
    }

    public Post createPost(Post post, String autorEmail) {
        Usuario autor = usuarioRepository.findByEmail(autorEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        post.setAutor(autor);
        post.setFechaCreacion(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public boolean deletePost(Long id, String userEmail) {
        Post post = getPostById(id);
        if (post == null) {
            return false;
        }
        if (!post.getAutor().getEmail().equals(userEmail)) {
            throw new SecurityException("No tienes permiso para eliminar este post");
        }
        postRepository.deleteById(id);
        return true;
    }
}
