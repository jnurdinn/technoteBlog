package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.repositories.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public Account save(Account account) {

    account.setCreatedAt(LocalDateTime.now());
    account.setPassword(passwordEncoder.encode(account.getPassword()));

    return accountRepository.saveAndFlush(account);

  }

  public Optional<Account> findByEmailAddress(String emailAddress) {
    return accountRepository.findOneByEmailAddress(emailAddress);
  }


  public Optional<Account> findById(Long id) {
    return accountRepository.findById(id);
  }

}
