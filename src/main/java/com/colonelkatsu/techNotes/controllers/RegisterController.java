package com.colonelkatsu.techNotes.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.services.AccountService;

@Controller
public class RegisterController {

  @Autowired
  private AccountService accountService;

  @GetMapping("/register")
  public String getRegister(Model model) {
    Account account = new Account();
    model.addAttribute("account", account);
    return("register");
  }

  @PostMapping("/register")
  public String registerNewUser(@ModelAttribute Account account) {
    account.setCreatedAt(LocalDateTime.now());
    accountService.save(account);

    return("redirect:/");
  }

}

