package com.colonelkatsu.techNotes.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.colonelkatsu.techNotes.entity.Post;
import com.colonelkatsu.techNotes.services.PostService;

@Controller
public class HomeController {

  @Autowired
  private PostService postService;

  @GetMapping({"/", "/posts"})
  public String home(Model model) {

    List<Post> posts = postService.getAll();

    Collections.reverse(posts);
    model.addAttribute("posts", posts);

    return("index");
  }

  @GetMapping("/search")
  public String search(Model model, @RequestParam String keyword) {

    List<Post> posts = List.of();

    if (keyword.isBlank()){
      posts = postService.getAll();
    } else {
      posts = postService.getByKeywordForRender(keyword);
    }

    Collections.reverse(posts);
    model.addAttribute("posts", posts);

    return("index");
  }

  @GetMapping({"/category/{category}"})
  public String categoryCloud(Model model, @PathVariable String category) {

    List<Post> posts = postService.getByCategoryForRender(category);

    Collections.reverse(posts);
    model.addAttribute("posts", posts);

    return("index");
  }

}