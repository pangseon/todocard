package com.example.todocard.repository;

import com.example.todocard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreateDateDesc();

//    Optional<User> findByUserId(String userId);
}
