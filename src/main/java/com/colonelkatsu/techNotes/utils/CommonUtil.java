package com.colonelkatsu.techNotes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

  public static String datetimeFormatter(LocalDateTime localDateTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDateTime = localDateTime.format(dateTimeFormatter);

    return formattedDateTime;
  }

}
