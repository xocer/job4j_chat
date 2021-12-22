package com.example.job4j_chat.controller;

import com.example.job4j_chat.modal.Message;
import com.example.job4j_chat.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/message")
public class MessageController {
    private MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Integer id) {
        var message = messageRepository.findById(id);
        return new ResponseEntity<>(message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return new ResponseEntity<>(messageRepository.save(message), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        if (message.getId() == null) {
            throw new NullPointerException("Id cannot be null");
        }
        messageRepository.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        messageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
