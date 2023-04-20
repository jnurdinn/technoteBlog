package com.colonelkatsu.techNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colonelkatsu.techNotes.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
