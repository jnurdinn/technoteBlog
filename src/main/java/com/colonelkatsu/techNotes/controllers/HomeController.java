package com.colonelkatsu.techNotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.colonelkatsu.techNotes.entity.Post;
import com.colonelkatsu.techNotes.services.PostService;
import com.colonelkatsu.techNotes.utils.CommonUtil;

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

  @GetMapping("/search")
  public String search(Model model, @RequestParam String keyword) {

    

    if (keyword.isBlank()){
      List<Post> posts = postService.getAll();

      model.addAttribute("posts", posts);
    } else {
      List<Post> posts = postService.getByKeywordForRender(keyword);

      model.addAttribute("posts", posts);
    }

    return("index");
  }

}