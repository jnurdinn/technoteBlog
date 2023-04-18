package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.colonelkatsu.techNotes.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String>  {

}
