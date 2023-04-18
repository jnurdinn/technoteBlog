package com.colonelkatsu.techNotes.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Image {
  private String filename;

  private String url;

  private LocalDateTime uploadedAt;
}
