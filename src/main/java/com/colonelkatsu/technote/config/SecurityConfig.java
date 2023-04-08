package com.colonelkatsu.technote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeHttpRequests()
      .requestMatchers("/api/auth/**").permitAll()
      .anyRequest().authenticated()
      .and().httpBasic();
    return httpSecurity.build();
  }

}