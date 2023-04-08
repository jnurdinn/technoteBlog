package com.colonelkatsu.technote.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colonelkatsu.technote.model.Post;
import com.colonelkatsu.technote.repository.PostRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Optional<Post> getById(Long id){
    return postRepository.findById(id);
  }

  public List<Post> getAll(){
    return postRepository.findAll();
  }

  public Post save(Post post) {
    if (post.getId() == null) {
      post.setCreatedAt(LocalDateTime.now());
    }

    return postRepository.save(post);
  }

}
