package com.colonelkatsu.techNotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

  @GetMapping("/messages")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String messages(Model model) {
    
    List<Message> messages = messageService.getAll();
    model.addAttribute("messages", messages);
    return("static/messages");
  }

}