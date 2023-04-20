package com.colonelkatsu.techNotes.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageInfo {

  private Long id;

  private String filename;
  
  private String url;

}
