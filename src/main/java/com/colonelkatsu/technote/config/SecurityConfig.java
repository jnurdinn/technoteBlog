package com.colonelkatsu.technote.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

//  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//      return authenticationManagerBean();
//  }

  @Bean
  SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable().authorizeHttpRequests()
      .requestMatchers("/api/auth/**").permitAll()
      .anyRequest().authenticated()
      .and().httpBasic();
    return httpSecurity.build();
  }

  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

}