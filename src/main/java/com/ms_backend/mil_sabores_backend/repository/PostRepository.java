package com.ms_backend.mil_sabores_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms_backend.mil_sabores_backend.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
