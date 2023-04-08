package com.colonelkatsu.technote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeHttpRequests()
      .requestMatchers("/api/auth/**").permitAll()
      .anyRequest().authenticated()
      .and().httpBasic();
    return httpSecurity.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}