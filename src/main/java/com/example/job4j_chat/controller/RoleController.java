package com.example.job4j_chat.controller;

import com.example.job4j_chat.modal.Role;
import com.example.job4j_chat.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Integer id) {
        var role = roleRepository.findById(id);
        return new ResponseEntity<>(role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return new ResponseEntity<>(roleRepository.save(role), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        if (role.getId() == null) {
            throw new NullPointerException("Id cannot be null");
        }
        roleRepository.save(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
