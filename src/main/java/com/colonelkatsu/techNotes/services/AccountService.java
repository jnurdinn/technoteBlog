package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.repositories.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public Account save(Account account) {

    account.setCreatedAt(LocalDateTime.now());

    return accountRepository.save(account);

  }

}
