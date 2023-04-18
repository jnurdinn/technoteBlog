package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.repositories.PostRepository;
import com.colonelkatsu.techNotes.utils.CommonConstant;
import com.colonelkatsu.techNotes.utils.CommonUtil;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Optional<Post> getById(Long id) {
    return postRepository.findById(id);
  }

  public Optional<Post> getByIdForRender(Long id) {
    Optional<Post> selectedPost = postRepository.findById(id);

    selectedPost.get().setBody(CommonUtil.markdownToHtml(selectedPost.get().getBody()));

    return selectedPost;
  }

  public List<Post> getAll() {
    List<Post> allPosts = postRepository.findAll();

    if (allPosts.isEmpty()) {
      return List.of();
    }

    for (int i = 0; i < allPosts.size(); i++) {

      String bodyPost = CommonUtil.markdownToHtml(allPosts.get(i).getBody());
      int maxChar = CommonConstant.homePostLimiter;

      if (bodyPost.length() > maxChar) {    
        bodyPost = bodyPost.substring(0, maxChar) + "...";
      }

      allPosts.get(i).setBody(bodyPost);

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
