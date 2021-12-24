package com.example.job4j_chat.repository;

import com.example.job4j_chat.modal.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByUsername(String username);

    List<Person> findAll();
}
