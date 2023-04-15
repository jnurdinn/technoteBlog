package com.colonelkatsu.techNotes.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
        @Parameter(name = "sequence_name", value = "contact_sequence"),
        @Parameter(name = "initial_value", value = "15000"),
        @Parameter(name = "increment_size", value = "1")
    })
    private Long id;

    private String firstname;

    private String lastname;

    private String emailAddress;

    private String message;

    private LocalDateTime createdAt;
}
