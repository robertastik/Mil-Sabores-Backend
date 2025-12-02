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
    public ResponseEntity<?> createPost(@RequestBody Post post, Principal principal) {
        System.out.println("=== POST /api/posts received ===");
        System.out.println("Principal: " + principal);
        if (principal == null) {
            return ResponseEntity.status(403).body("No authenticated user");
        }
        String autorEmail = principal.getName();
        System.out.println("Autor email: " + autorEmail);
        
        try {
            Post nuevoPost = postService.createPost(post, autorEmail);
            return ResponseEntity.ok(nuevoPost);
        } catch (Exception e) {
            System.err.println("Error creating post: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) {
        try {
            String userEmail = principal.getName();
            boolean deleted = postService.deletePost(id, userEmail);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
