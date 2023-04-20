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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.colonelkatsu.techNotes.entity.Account;
import com.colonelkatsu.techNotes.entity.Authority;
import com.colonelkatsu.techNotes.models.AccountInfo;
import com.colonelkatsu.techNotes.repositories.AuthorityRepository;
import com.colonelkatsu.techNotes.services.AccountService;

@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @GetMapping("/accounts/register")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String getRegister(Model model) {
    AccountInfo registerAccount = new AccountInfo();
    model.addAttribute("registerAccount", registerAccount);
    return("account/register");
  }

  @PostMapping("/accounts/register")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String registerNewUser(@ModelAttribute AccountInfo registerAccount, RedirectAttributes redirectAttributes) {
    
    if(accountService.findByEmailAddress(registerAccount.getEmailAddress()).isPresent()){
      redirectAttributes.addFlashAttribute("message", "Account already exists: " + registerAccount.getEmailAddress());
      return("redirect:/accounts/register");
    }

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

    redirectAttributes.addFlashAttribute("message", "Account created successfully: " + account.getEmailAddress());

    return("redirect:/accounts");
  }

  @GetMapping("/accounts")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String accountConfig(Model model) {
    
    List<Account> accounts = accountService.getAll();
    model.addAttribute("accounts", accounts);
    return("account/config");
  }

  @GetMapping("/accounts/id/{id}/edit")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String getEditAccount(Model model, @PathVariable Long id ){
    

    Optional<Account> optionalAccount = accountService.findById(id);

    if(optionalAccount.isPresent()) {
      Account editAccount = optionalAccount.get();

      model.addAttribute("editAccount", editAccount);

      return("account/edit");
    }

    return("error/404");
  }

  @PostMapping("/accounts/id/{id}/edit")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String submitEditAccount(@PathVariable Long id, @ModelAttribute Account editAccount, RedirectAttributes redirectAttributes) {

    Optional<Account> optionalAccount = accountService.findById(id);
    if (optionalAccount.isPresent()) {
      Account existingAccount = optionalAccount.get();

      existingAccount.setFirstname(editAccount.getFirstname());
      existingAccount.setLastname(editAccount.getLastname());
      existingAccount.setPassword(editAccount.getPassword());
      accountService.save(existingAccount);

      redirectAttributes.addFlashAttribute("message", "Account edited successfully: " + editAccount.getEmailAddress());

      return("redirect:/accounts");
    }

    return("error/404");
  }

  @GetMapping("/accounts/id/{id}/delete")
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

