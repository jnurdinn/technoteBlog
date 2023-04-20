package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.colonelkatsu.techNotes.entity.Account;
import com.colonelkatsu.techNotes.repositories.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<Account> getAll(){
    return accountRepository.findAll();
  }

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

  public void delete(Account account) {
    accountRepository.delete(account);
  }

}
