package com.colonelkatsu.techNotes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterAccount {
  private String password;

  private String firstname;

  private String lastname;

  private String emailAddress;

  private boolean adminRole;
}
