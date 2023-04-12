package com.colonelkatsu.techNotes.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Authority;
import com.colonelkatsu.techNotes.models.RegisterAccount;
import com.colonelkatsu.techNotes.repositories.AuthorityRepository;
import com.colonelkatsu.techNotes.services.AccountService;

@Controller
public class RegisterController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @GetMapping("/register")
  public String getRegister(Model model) {
    RegisterAccount registerAccount = new RegisterAccount();
    model.addAttribute("registerAccount", registerAccount);
    return("admin/register");
  }

  @PostMapping("/register")
  public String registerNewUser(@ModelAttribute RegisterAccount registerAccount) {
    Account account = new Account();

    account.setFirstname(registerAccount.getFirstname());
    account.setLastname(registerAccount.getLastname());
    account.setEmailAddress(registerAccount.getEmailAddress());
    account.setPassword(registerAccount.getPassword());
    account.setCreatedAt(LocalDateTime.now());

    Set<Authority> authorities = new HashSet<>();
    authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
    if (registerAccount.isAdminRole()) {
      authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities::add);
    }
    account.setAuthorities(authorities);

    accountService.save(account);

    return("redirect:/");
  }

}

