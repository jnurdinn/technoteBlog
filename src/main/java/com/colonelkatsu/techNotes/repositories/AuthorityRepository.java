package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colonelkatsu.techNotes.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String>  {

}
