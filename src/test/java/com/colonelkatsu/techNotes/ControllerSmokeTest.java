package com.colonelkatsu.techNotes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.colonelkatsu.techNotes.controllers.HomeController;
import com.colonelkatsu.techNotes.controllers.LoginController;
import com.colonelkatsu.techNotes.controllers.PostController;
import com.colonelkatsu.techNotes.controllers.StaticController;
import com.colonelkatsu.techNotes.controllers.UploadController;
import com.colonelkatsu.techNotes.controllers.AdminController;

@SpringBootTest
public class ControllerSmokeTest {

  @Autowired
  private HomeController homeController;

  @Autowired
  private AdminController adminController;

  @Autowired
  private LoginController loginController;

  @Autowired
  private PostController postController;

  @Autowired
  private StaticController staticController;

  @Autowired
  private UploadController uploadController;

  @Test
  public void smokeTest() throws Exception {
    assertThat(homeController).isNotNull();
    assertThat(adminController).isNotNull();
    assertThat(loginController).isNotNull();
    assertThat(postController).isNotNull();
    assertThat(staticController).isNotNull();
    assertThat(uploadController).isNotNull();
  }



}
