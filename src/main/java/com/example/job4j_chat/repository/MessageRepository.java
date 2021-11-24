package com.example.job4j_chat.repository;

import com.example.job4j_chat.modal.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
