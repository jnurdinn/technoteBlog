package com.colonelkatsu.techNotes.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.PostService;

@Controller
public class HomeController {

  @Autowired
  private PostService postService;

  @GetMapping({"/", "/posts"})
  public String home(Model model) {

    List<Post> posts = postService.getAll();
    model.addAttribute("posts", posts);

    return("index");
  }

}
