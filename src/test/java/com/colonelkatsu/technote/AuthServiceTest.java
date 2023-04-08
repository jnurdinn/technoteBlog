package com.colonelkatsu.technote;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import com.colonelkatsu.technote.model.User;
import com.colonelkatsu.technote.repository.UserRepository;
import com.colonelkatsu.technote.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class AuthServiceTest {

  @Mock
  AuthService authTestServiceMock;

  @Autowired
  UserRepository userRepository;

  @Autowired
  User user;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void createStudentHttpRequest() throws Exception {

      user.setUserName("user1");
      user.setPassword("12345678");
      user.setEmail("user1@test.com");

      mockMvc.perform(post("/api/auth/signup")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(user)))
              .andExpect(status().isOk());

      Optional<User> verifyUser = userRepository.findByUserName("user1");
      assertNotNull(verifyUser, "User should be valid.");
  }

  @AfterEach
  public void setupAfterTransaction() {
      userRepository.deleteAll();
  }

}
