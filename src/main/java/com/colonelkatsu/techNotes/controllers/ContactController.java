package com.colonelkatsu.techNotes.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.colonelkatsu.techNotes.entity.Message;
import com.colonelkatsu.techNotes.services.MessageService;

@Controller
public class ContactController {

  @Autowired
  private MessageService messageService;

  @GetMapping("/messages")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String getMessages(Model model) {
    
    List<Message> messages = messageService.getAll();
    model.addAttribute("messages", messages);
    return("contact/read");
  }

  @GetMapping("/contact")
  public String formNewMessage(Model model) {
    Message message = new Message();
    model.addAttribute("message", message);
    return("contact/new");
  }

  @PostMapping("/contact")
  public String sendNewMessage(@ModelAttribute Message message, RedirectAttributes redirectAttributes) {
    messageService.save(message);
    redirectAttributes.addFlashAttribute("message", "Message sent successfully. We'll get back to you!");

    return("redirect:/");
  }

}

