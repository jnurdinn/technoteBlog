package com.colonelkatsu.techNotes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.colonelkatsu.techNotes.entity.Post;

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

  public static List<Post> listOfRenderedPosts(List<Post> posts){
    if (posts.isEmpty()) {
      return List.of();
    }

    for (int i = 0; i < posts.size(); i++) {

      String bodyPost = CommonUtil.markdownToHtml(posts.get(i).getBody());
      int maxChar = CommonConstant.homePostLimiter;

      if (bodyPost.length() > maxChar) {    
        bodyPost = bodyPost.substring(0, maxChar) + "...";
      }

      posts.get(i).setBody(bodyPost);

    }
    return posts;
  }
}
