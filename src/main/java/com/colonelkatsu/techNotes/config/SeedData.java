package com.colonelkatsu.techNotes.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Authority;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.repositories.AuthorityRepository;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.PostService;

@Component
public class SeedData implements CommandLineRunner {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public void run(String... args) throws Exception {
    List<Post> posts = postService.getAll();

    if (posts.size() == 0) {

      Authority user = new Authority();
      user.setName("ROLE_USER");
      authorityRepository.save(user);

      Authority admin = new Authority();
      admin.setName("ROLE_ADMIN");
      authorityRepository.save(admin);

      Account account1 = new Account();
      Account account2 = new Account();

      account1.setFirstname("Rikka");
      account1.setLastname("Takarada");
      account1.setEmailAddress("rikkatakarada@test.com");
      account1.setPassword("12345678");
      Set<Authority> authorities1 = new HashSet<>();
      authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities1::add);
      authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
      account1.setAuthorities(authorities1);

      account2.setFirstname("Yume");
      account2.setLastname("Minami");
      account2.setEmailAddress("yumeminami@test.com");
      account2.setPassword("12345678");
      Set<Authority> authorities2 = new HashSet<>();
      authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
      account2.setAuthorities(authorities2);

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
