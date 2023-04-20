package com.colonelkatsu.techNotes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

@Component
public class CommonUtil {

  public static String datetimeFormatter(LocalDateTime localDateTime) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDateTime = localDateTime.format(dateTimeFormatter);

    return formattedDateTime;
  }

  public static String markdownToHtml(String markdown){
    Parser parser = Parser.builder().build();

    Node document = parser.parse(markdown);
    HtmlRenderer renderer = HtmlRenderer.builder().build();

    return renderer.render(document);
  }

  public static String keywordQuery(String keyword){
    //return "select * from POST p where p.TITLE like '%" + keyword + "%' or p.BODY like '%" + keyword + "%';";
    return "select * from POST p where p.TITLE like '%post%' or p.BODY like '%post%';";
  }

}
