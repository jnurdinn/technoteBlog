package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colonelkatsu.techNotes.models.Comment;
import com.colonelkatsu.techNotes.repositories.CommentRepository;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public List<Comment> getAll(){
    return commentRepository.findAll();
  }

  public Comment save(Comment comment) {

    comment.setCreatedAt(LocalDateTime.now());
    return commentRepository.save(comment);

  }

}