package com.colonelkatsu.techNotes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.colonelkatsu.techNotes.models.Account;

@Component("userDetailsService")
public class LoginUserDetailsService implements UserDetailsService {

  @Autowired
  private AccountService accountService;

  @Override
  public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
    Optional<Account> optionalAccount = accountService.findByEmailAddress(emailAddress);

    if (optionalAccount.isEmpty()) {
      throw new UsernameNotFoundException("Email is not registered");
    }

    Account account = optionalAccount.get();

    List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName()))
        .collect(Collectors.toList());


    return new org.springframework.security.core.userdetails.User(account.getEmailAddress(),
        account.getPassword(), grantedAuthorities);
  }

}
