package com.colonelkatsu.techNotes.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String body;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name= "account_id", referencedColumnName = "id", nullable = false)
  private Account account;

  @Override
  public String toString() {
    return "Post{" +
        "id='" + id + "'" +
        "title='" + title + "'" +
        "createdAt='" + createdAt + "'" +
        "updatedAt='" + updatedAt + "'" +
        "}";
  }
}
