package com.colonelkatsu.technote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.colonelkatsu.technote.model.Post;
import com.colonelkatsu.technote.service.PostService;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class PostTest {

  @Autowired
  private PostService postService;

  @Test
  public void createPost() throws Exception {

    List<Post> posts = postService.getAll();

    assertNull(posts, "Posts should be empty.");

    Post post1 = new Post();
    post1.setTitle("This is post 1");
    post1.setBody("This is the body of post 1");

    Post post2 = new Post();
    post2.setTitle("This is post 2");
    post2.setBody("This is the body of post 2");

    postService.save(post1);
    postService.save(post2);

    posts = postService.getAll();

    assertEquals(posts.size(),2);
    assertEquals(posts.get(0).getTitle(),"This is post 1");
    assertEquals(posts.get(1).getTitle(),"This is post 2");
  }
}