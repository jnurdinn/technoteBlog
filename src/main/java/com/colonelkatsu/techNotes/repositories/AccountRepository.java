package com.colonelkatsu.techNotes.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.colonelkatsu.techNotes.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findOneByEmailAddress(String emailAddress);
}