package com.colonelkatsu.techNotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

  private static final String[] WHITELIST = {"/", "/register", "/h2-console/*", "/**/*.{js,html,css,svg,png}"};

  @Bean
  static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeHttpRequests()
        .antMatchers(WHITELIST).permitAll()
        .antMatchers(HttpMethod.GET, "/posts/*").permitAll()
        .anyRequest().authenticated();

    httpSecurity.formLogin().loginPage("/login").loginProcessingUrl("/login")
        .usernameParameter("emailAddress").passwordParameter("password")
        .defaultSuccessUrl("/", true).failureUrl("/login?error").permitAll().and().logout()
        .logoutUrl("/logout").logoutSuccessUrl("/login?logout").and().httpBasic();

    // Remove the following if migrating from H2
    httpSecurity.csrf().disable().headers().frameOptions().disable();

    return httpSecurity.build();
  }

}
