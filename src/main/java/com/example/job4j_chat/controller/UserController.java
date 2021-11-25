package com.example.job4j_chat.controller;

import com.example.job4j_chat.modal.Person;
import com.example.job4j_chat.repository.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    private PersonRepository personRepository;
    private BCryptPasswordEncoder encoder;

    public UserController(PersonRepository personRepository,
                          BCryptPasswordEncoder encoder) {
        this.personRepository = personRepository;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}