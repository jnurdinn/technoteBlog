package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colonelkatsu.techNotes.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
