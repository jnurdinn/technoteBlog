package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colonelkatsu.techNotes.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
