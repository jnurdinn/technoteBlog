package com.colonelkatsu.techNotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.colonelkatsu.techNotes.models.Message;
import com.colonelkatsu.techNotes.services.MessageService;

@Controller
public class StaticController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/about-us")
  public String aboutMe(Model model) {
    return("static/about-us");
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    Message message = new Message();
    model.addAttribute("message", message);
    return("static/contact");
  }

  @PostMapping("/contact")
  public String registerNewUser(@ModelAttribute Message message) {
    messageService.save(message);
    return("redirect:/");
  }

}