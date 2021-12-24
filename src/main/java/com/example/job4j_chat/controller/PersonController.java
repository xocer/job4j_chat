package com.example.job4j_chat.controller;

import com.example.job4j_chat.modal.Person;
import com.example.job4j_chat.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/person")
public class PersonController {
    private static Logger logger = LoggerFactory.getLogger(PersonController.class.getSimpleName());
    private PersonRepository personRepository;
    private ObjectMapper objectMapper;

    public PersonController(PersonRepository personRepository, ObjectMapper objectMapper) {
        this.personRepository = personRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        var personList = personRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personList);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than or equal to zero");
        }

        return personRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        if (person.getId() == null) {
            throw new NullPointerException("Id cannot be null");
        }
        personRepository.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        personRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        logger.error(e.getLocalizedMessage());
    }
}
