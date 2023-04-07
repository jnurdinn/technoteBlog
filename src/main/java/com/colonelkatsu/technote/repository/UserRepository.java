package com.colonelkatsu.technote.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.colonelkatsu.technote.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByUserName(String username);
}
