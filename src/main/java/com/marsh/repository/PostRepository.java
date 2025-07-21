package com.marsh.repository;

import com.marsh.repository.entitiy.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
