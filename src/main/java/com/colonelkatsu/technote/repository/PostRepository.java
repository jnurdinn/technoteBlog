package com.colonelkatsu.technote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.colonelkatsu.technote.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
