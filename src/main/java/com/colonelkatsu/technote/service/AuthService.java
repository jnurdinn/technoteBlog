package com.colonelkatsu.technote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.colonelkatsu.technote.entity.RegisterEntity;
import com.colonelkatsu.technote.model.User;
import com.colonelkatsu.technote.repository.UserRepository;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @SuppressWarnings("rawtypes")
  public ResponseEntity signUp(RegisterEntity registerRequest) {

    User user = new User();
    user.setUserName(registerRequest.getUsername());
    user.setPassword(registerRequest.getPassword());
    user.setEmail(registerRequest.getEmail());

    userRepository.save(user);

    return new ResponseEntity(HttpStatus.OK);

  }

}
