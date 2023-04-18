package com.colonelkatsu.techNotes.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
      @Parameter(name = "sequence_name", value = "account_sequence"),
      @Parameter(name = "initial_value", value = "1000"),
      @Parameter(name = "increment_size", value = "1")
  })
  private Long id;

  private String password;

  private String firstname;

  private String lastname;

  private String emailAddress;

  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "account")
  private List<Post> posts;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "account_authority", joinColumns = {
      @JoinColumn(name = "account_id", referencedColumnName = "id") }, inverseJoinColumns = {
          @JoinColumn(name = "authority_name", referencedColumnName = "name") })
  private Set<Authority> authorities = new HashSet<>();

  @Override
  public String toString() {
    return "Authority{" +
        "firstname='" + firstname + "'" +
        "lastname='" + lastname + "'" +
        "emailAddress='" + emailAddress + "'" +
        "authorities='" + authorities + "'" +
        "}";
  }
}
