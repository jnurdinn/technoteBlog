package com.colonelkatsu.techNotes.models;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String password;

  private String firstname;

  private String lastname;

  private String emailAddress;

  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "account")
  private List<Post> posts;
}
