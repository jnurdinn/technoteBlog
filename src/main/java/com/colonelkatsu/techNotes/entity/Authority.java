package com.colonelkatsu.techNotes.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(length = 16)
  private String name;

  @Override
  public String toString() {
    return "Authority{"
        + "name='" + name + "'" + "}";
  }
}
