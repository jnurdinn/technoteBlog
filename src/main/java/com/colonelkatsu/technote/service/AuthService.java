package com.colonelkatsu.technote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.colonelkatsu.technote.entity.RegisterEntity;
import com.colonelkatsu.technote.model.User;
import com.colonelkatsu.technote.repository.UserRepository;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @SuppressWarnings("rawtypes")
  public ResponseEntity signup(RegisterEntity registerRequest) {

    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    user.setPassword(encodePassword(registerRequest.getPassword()));

    userRepository.save(user);

    return new ResponseEntity(HttpStatus.OK);

  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

}
