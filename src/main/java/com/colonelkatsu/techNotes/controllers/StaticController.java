package com.colonelkatsu.techNotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

  @GetMapping("/about-us")
  public String aboutMe(Model model) {
    return("static/about-us");
  }

  @GetMapping("/markdown-guide")
  public String markdownGuide(Model model) {
    return("static/markdown-guide");
  }

}