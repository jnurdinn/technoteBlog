package com.colonelkatsu.technote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.colonelkatsu.technote.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
