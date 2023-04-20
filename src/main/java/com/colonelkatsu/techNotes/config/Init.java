package com.colonelkatsu.techNotes.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.colonelkatsu.techNotes.entity.Account;
import com.colonelkatsu.techNotes.entity.Authority;
import com.colonelkatsu.techNotes.entity.Post;
import com.colonelkatsu.techNotes.repositories.AuthorityRepository;
import com.colonelkatsu.techNotes.services.AccountService;
import com.colonelkatsu.techNotes.services.PostService;
import com.colonelkatsu.techNotes.services.UploadService;

@Component
public class Init implements CommandLineRunner {

  @Autowired
  private PostService postService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private AuthorityRepository authorityRepository;

  @Autowired
  private UploadService uploadService;

  @Override
  public void run(String... args) throws Exception {
    List<Account> accounts = accountService.getAll();
    List<Post> posts = postService.getAll();

    if (accounts.size() == 0 && posts.size() == 0) {

      Authority user = new Authority();
      user.setName("ROLE_USER");
      authorityRepository.save(user);

      Authority admin = new Authority();
      admin.setName("ROLE_ADMIN");
      authorityRepository.save(admin);

      Account adminAccount = new Account();
      adminAccount.setFirstname("Admin");
      adminAccount.setLastname("Admin");
      adminAccount.setEmailAddress("admin@katsu.icu");
      adminAccount.setPassword("password12345678");

      Set<Authority> adminAuthorities = new HashSet<>();
      authorityRepository.findById("ROLE_ADMIN").ifPresent(adminAuthorities::add);
      authorityRepository.findById("ROLE_USER").ifPresent(adminAuthorities::add);
      adminAccount.setAuthorities(adminAuthorities);

      accountService.save(adminAccount);
      
      uploadService.init();
    }
  }

}
