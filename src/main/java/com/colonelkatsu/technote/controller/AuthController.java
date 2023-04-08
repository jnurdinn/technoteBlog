package com.colonelkatsu.technote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.colonelkatsu.technote.entity.RegisterEntity;
import com.colonelkatsu.technote.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/signup")
  public void signup(@RequestBody RegisterEntity registerEntity) {
    authService.signup(registerEntity);
  }

}
