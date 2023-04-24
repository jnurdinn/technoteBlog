package com.colonelkatsu.techNotes.controllers;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.colonelkatsu.techNotes.entity.Account;
import com.colonelkatsu.techNotes.entity.Post;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.PostService;

@Controller
public class PostController {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @GetMapping("/posts/id/{id}")
  public String getPost(@PathVariable Long id, Model model) {

    Optional<Post> optionalPost = postService.getByIdForRender(id);

    if(optionalPost.isPresent()) {
      Post post = optionalPost.get();
      model.addAttribute("post", post);

      return "post/read";
    }

    return("error/404");
  }

  @GetMapping("/posts/new")
  @PreAuthorize("isAuthenticated()")
  public String createNewPost(Model model, Principal principal) {

    String authUsername = "anonymousUser";

    if (principal != null) {
      authUsername = principal.getName();
    }

    Optional<Account> optionalAccount = accountService.findByEmailAddress(authUsername);

    if(optionalAccount.isPresent()) {
      Post post = new Post();
      post.setAccount(optionalAccount.get());
      model.addAttribute("post", post);
      return "post/new";
    }

    return("error/403");
  }

  @PostMapping("/posts/new")
  @PreAuthorize("isAuthenticated()")
  public String submitNewPost(@ModelAttribute Post post) {
    post.setCreatedAt(LocalDateTime.now());
    postService.save(post);

    return("redirect:/posts/id/" + post.getId());
  }

  @GetMapping("/posts/id/{id}/edit")
  @PreAuthorize("isAuthenticated()")
  public String getExistingPost(@PathVariable Long id, Model model) {

    Optional<Post> optionalPost = postService.getById(id);

    if(optionalPost.isPresent()) {
      Post post = optionalPost.get();
      model.addAttribute("post", post);
      return "post/edit";
    }

    return("error/404");
  }

  @PostMapping("/posts/id/{id}")
  @PreAuthorize("isAuthenticated()")
  public String submitEditedPost(@PathVariable Long id, Post post, BindingResult result, Model model) {

    Optional<Post> optionalPost = postService.getById(id);

    if (optionalPost.isPresent()) {
      Post existingPost = optionalPost.get();

      existingPost.setTitle(post.getTitle());
      existingPost.setBody(post.getBody());
      existingPost.setUpdatedAt(LocalDateTime.now());
      existingPost.setCategory(post.getCategory());

      postService.save(existingPost);

      return("redirect:/posts/id/" + post.getId());
    }

    return("error/404");
  }

  @GetMapping("/posts/id/{id}/delete")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String deletePost(@PathVariable Long id) {

    Optional<Post> optionalPost = postService.getById(id);

    if(optionalPost.isPresent()) {
      Post post = optionalPost.get();

      postService.delete(post);

      return "redirect:/";
    }

    return("error/404");
  }
}
