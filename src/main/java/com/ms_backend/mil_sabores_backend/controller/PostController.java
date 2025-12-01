package com.ms_backend.mil_sabores_backend.controller;

import java.security.Principal;
import java.util.List;

import com.ms_backend.mil_sabores_backend.model.Post;
import com.ms_backend.mil_sabores_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal) {
        String autorEmail = principal.getName();
        
        Post nuevoPost = postService.createPost(post, autorEmail);
        return ResponseEntity.ok(nuevoPost);
    }
}
