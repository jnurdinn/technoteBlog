package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.colonelkatsu.techNotes.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
