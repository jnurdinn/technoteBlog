package com.colonelkatsu.technote.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.colonelkatsu.technote.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

  Optional<User> findByUserName(String username);
}
