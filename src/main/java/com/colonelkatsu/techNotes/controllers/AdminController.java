package com.colonelkatsu.techNotes.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Authority;
import com.colonelkatsu.techNotes.models.Message;
import com.colonelkatsu.techNotes.models.RegisterAccount;
import com.colonelkatsu.techNotes.repositories.AuthorityRepository;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.MessageService;

@Controller
public class AdminController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private MessageService messageService;

  @GetMapping("/register")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String getRegister(Model model) {
    RegisterAccount registerAccount = new RegisterAccount();
    model.addAttribute("registerAccount", registerAccount);
    return("admin/register");
  }

  @PostMapping("/register")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

    return("redirect:/accounts");
  }

  @GetMapping("/messages")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String messages(Model model) {
    
    List<Message> messages = messageService.getAll();
    model.addAttribute("messages", messages);
    return("admin/messages");
  }

  @GetMapping("/accounts")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String accountConfig(Model model) {
    
    List<Account> accounts = accountService.getAll();
    model.addAttribute("accounts", accounts);
    return("admin/account-config");
  }

  @GetMapping("/accounts/{id}/delete")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String deleteAccount(@PathVariable Long id) {

    if(id.equals(Long.valueOf(1000))){
      return("error/403");
    }

    Optional<Account> optionalAccount = accountService.findById(id);

    if(optionalAccount.isPresent()) {
      Account account = optionalAccount.get();

      accountService.delete(account);

      return "redirect:/accounts";
    }

    return("error/404");
  }

}

