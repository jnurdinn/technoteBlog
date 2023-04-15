package com.colonelkatsu.techNotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.colonelkatsu.techNotes.models.Comment;
import com.colonelkatsu.techNotes.services.CommentService;

@Controller
public class StaticController {

  @Autowired
  private CommentService commentService;

  @GetMapping("/about-us")
  public String aboutMe(Model model) {
    return("static/about-us");
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    Comment comment = new Comment();
    model.addAttribute("comment", comment);
    return("static/contact");
  }

  @PostMapping("/contact")
  public String registerNewUser(@ModelAttribute Comment comment) {
    commentService.save(comment);
    return("redirect:/");
  }

}