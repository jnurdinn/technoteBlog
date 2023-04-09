package com.colonelkatsu.techNotes.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.PostService;
import com.colonelkatsu.techNotes.utils.CommonUtil;

@Controller
public class HomeController {

  @Autowired
  private PostService postService;

  @GetMapping("/")
  public String home(Model model) {

    List<Post> posts = postService.getAll();
    ArrayList<String> formattedDates = new ArrayList<String>();

    model.addAttribute("posts", posts);
    posts.forEach((post) -> {
      formattedDates.add(CommonUtil.datetimeFormatter(post.getCreatedAt()));
    });

    return("home");
  }

}
