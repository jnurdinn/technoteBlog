package com.colonelkatsu.techNotes.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.PostService;

@Controller
public class PostController {

  @Autowired
  private PostService postService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {

    Optional<Post> optionalPost = postService.getById(id);

    if(optionalPost.isPresent()) {
      Post post = optionalPost.get();
      model.addAttribute("post", post);

      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      String formattedDateTime = post.getCreatedAt().format(dateTimeFormatter);
      model.addAttribute("createdAt", formattedDateTime);

      return "post";
    }

    return("error404");
  }

}
