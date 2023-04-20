package com.colonelkatsu.techNotes.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colonelkatsu.techNotes.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findOneByEmailAddress(String emailAddress);
}