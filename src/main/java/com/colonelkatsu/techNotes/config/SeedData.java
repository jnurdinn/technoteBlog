package com.colonelkatsu.techNotes.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.PostService;

@Component
public class SeedData implements CommandLineRunner {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Override
  public void run(String... args) throws Exception {
    List<Post> posts = postService.getAll();

    if (posts.size() == 0) {

      Account account1 = new Account();
      Account account2 = new Account();

      account1.setFirstname("Rikka");
      account1.setLastname("Takarada");
      account1.setEmailAddress("rikkatakarada@test.com");
      account1.setPassword("12345678");

      account2.setFirstname("Yume");
      account2.setLastname("Minami");
      account2.setEmailAddress("yumeminami@test.com");
      account2.setPassword("12345678");

      accountService.save(account1);
      accountService.save(account2);

      Post post1 = new Post();
      post1.setTitle("Post 1 title");
      post1.setBody("This is the body of post 1.");
      post1.setAccount(account1);

      Post post2 = new Post();
      post2.setTitle("Post 2 title");
      post2.setBody("This is the body of post 2.");
      post2.setAccount(account2);

      postService.save(post1);
      postService.save(post2);
    }

    // TODO : Remove seedData & use testing
  }

}
