package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colonelkatsu.techNotes.entity.Post;
import com.colonelkatsu.techNotes.repositories.PostRepository;
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
    return CommonUtil.listOfRenderedPosts(allPosts);
  }

  public List<Post> getByCategoryForRender(String category){
    List<Post> selectedPosts = postRepository.findByCategory(category);
    return CommonUtil.listOfRenderedPosts(selectedPosts);
  }

  public List<Post> getByKeywordForRender(String keyword){
    List<Post> selectedPosts = postRepository.findByKeyword(keyword);
    return CommonUtil.listOfRenderedPosts(selectedPosts);
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
