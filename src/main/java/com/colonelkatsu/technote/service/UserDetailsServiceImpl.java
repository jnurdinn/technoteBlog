package com.colonelkatsu.technote.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.colonelkatsu.technote.model.User;
import com.colonelkatsu.technote.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("No user found" + username));
    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
        true, true, true, true, getAuthorities("ROLE_USER"));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String roleUser) {
    return Collections.singletonList(new SimpleGrantedAuthority(roleUser));
  }

}
