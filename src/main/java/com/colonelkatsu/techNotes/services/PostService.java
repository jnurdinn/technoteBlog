package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.repositories.PostRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Optional<Post> getById(Long id) {
    return postRepository.findById(id);
  }

  public List<Post> getAll() {
    List<Post> allPosts = postRepository.findAll();

    if (allPosts.isEmpty()) {
      return null;
    }

    for (int i = 0; i < allPosts.size(); i++) {

      String bodyPost = allPosts.get(i).getBody();
      int maxChar = 125;

      if (bodyPost.length() > maxChar) {
        allPosts.get(i).setBody(bodyPost.substring(0, maxChar) + "...");
      }

    }
    return allPosts;
  }

  public Post save(Post post) {

    if (post.getId() == null) {
      post.setCreatedAt(LocalDateTime.now());
    } else {
      post.setUpdatedAt(LocalDateTime.now());
    }


    return postRepository.save(post);
  }

  public void delete(Post post) {
    postRepository.delete(post);
  }

}
