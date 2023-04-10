package com.colonelkatsu.techNotes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.colonelkatsu.techNotes.config.SecurityConfig;
import com.colonelkatsu.techNotes.controllers.PostController;
import com.colonelkatsu.techNotes.models.Account;
import com.colonelkatsu.techNotes.models.Post;
import com.colonelkatsu.techNotes.repositories.AccountRepository;
import com.colonelkatsu.techNotes.repositories.PostRepository;

@TestPropertySource("/application-test.properties")
@Import(SecurityConfig.class)
@WebMvcTest(PostController.class)
public class PostServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private PostRepository postRepository;

  @BeforeEach
  public void beforeTesting() {

    Account testAccount = new Account();

    testAccount.setFirstname("Test");
    testAccount.setLastname("Account");
    testAccount.setEmailAddress("testaccount@test.com");
    testAccount.setPassword("12345678");

    accountRepository.save(testAccount);

    Post post1 = new Post();
    post1.setTitle("Post 1 Title");
    post1.setBody("This is the body of post 1.");

    Post post2 = new Post();
    post2.setTitle("Post 2 Title");
    post2.setBody("This is the body of post 2.");

    postRepository.save(post1);
    postRepository.save(post2);
  }

  @Test
  public void getPost() throws Exception {
    this.mockMvc.perform(get("/posts/",1)).andExpect(status().isOk());
  }

  @AfterEach
  public void afterTesting() {
    accountRepository.deleteAll();
    postRepository.deleteAll();
  }
}
