package com.ms_backend.mil_sabores_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ms_backend.mil_sabores_backend.model.Post;
import com.ms_backend.mil_sabores_backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted((p1, p2) -> p2.getFechaCreacion().compareTo(p1.getFechaCreacion()))
                .toList();
    }

    public Post createPost(Post post, String autorEmail) {
        post.setAutorEmail(autorEmail);
        post.setFechaCreacion(LocalDateTime.now());
        return postRepository.save(post);
    }
}
