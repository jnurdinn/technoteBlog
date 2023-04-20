package com.colonelkatsu.techNotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.colonelkatsu.techNotes.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findByCategory(String category);

    @Query(value = "select * from POST p where p.TITLE like %:keyword% or p.BODY like %:keyword%", nativeQuery = true)
    List<Post> findByKeyword(@Param("keyword") String keyword);

}
