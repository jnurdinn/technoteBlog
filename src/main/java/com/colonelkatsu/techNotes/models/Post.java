package com.colonelkatsu.techNotes.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "post_sequence"),
      @Parameter(name = "initial_value", value = "5000"),
      @Parameter(name = "increment_size", value = "1")
  })
  private Long id;

  private String title;

  @Column(columnDefinition = "TEXT")
  private String body;

  private String category;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name= "account_id", referencedColumnName = "id", nullable = false)
  private Account account;

  @Override
  public String toString() {
    return "Post{" +
        "postId='" + id + "'" +
        "title='" + title + "'" +
        "createdAt='" + createdAt + "'" +
        "updatedAt='" + updatedAt + "'" +
        "}";
  }
}
