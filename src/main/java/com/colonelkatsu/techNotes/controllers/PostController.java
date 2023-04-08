package com.colonelkatsu.techNotes.controllers;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.PostService;

@Controller
public class PostController {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {

    Optional<Post> optionalPost = postService.getById(id);

    if(optionalPost.isPresent()) {
      Post post = optionalPost.get();
      model.addAttribute("post", post);

      return "post";
    }

    return("error404");
  }

  @GetMapping("/posts/new")
  public String createNewPost(Model model) {

    Optional<Account> optionalAccount = accountService.findByEmailAddress("rikkatakarada@test.com");

    if(optionalAccount.isPresent()) {
      Post post = new Post();
      post.setAccount(optionalAccount.get());
      model.addAttribute("post", post);
      return "postNew";
    }

    return("error403");
  }

  @PostMapping("/posts/new")
  public String registerNewUser(@ModelAttribute Post post) {
    post.setCreatedAt(LocalDateTime.now());
    postService.save(post);

    return("redirect:/posts/" + post.getId());
  }

}
