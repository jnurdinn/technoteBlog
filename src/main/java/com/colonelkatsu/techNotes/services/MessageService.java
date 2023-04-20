package com.colonelkatsu.techNotes.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colonelkatsu.techNotes.entity.Message;
import com.colonelkatsu.techNotes.repositories.MessageRepository;

@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public List<Message> getAll(){
    return messageRepository.findAll();
  }

  public Message save(Message message) {

    message.setCreatedAt(LocalDateTime.now());
    return messageRepository.save(message);

  }

}