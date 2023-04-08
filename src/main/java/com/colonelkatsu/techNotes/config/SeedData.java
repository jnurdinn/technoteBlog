package com.colonelkatsu.techNotes.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.PostService;

@Component
public class SeedData implements CommandLineRunner {

  @Autowired
  private PostService postService;

  @Override
  public void run(String... args) throws Exception {
    List<Post> posts = postService.getAll();

    if (posts.size() == 0) {
      Post post1 = new Post();
      post1.setTitle("Post 1 title");
      post1.setBody("This is the body of post 1.");

      Post post2 = new Post();
      post2.setTitle("Post 2 title");
      post2.setBody("This is the body of post 2.");

      postService.save(post1);
      postService.save(post2);
    }

    // TODO : Remove seedData & use testing
  }

}
