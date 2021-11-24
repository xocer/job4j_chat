package com.example.job4j_chat.repository;

import com.example.job4j_chat.modal.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
