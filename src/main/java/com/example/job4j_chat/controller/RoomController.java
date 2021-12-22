package com.example.job4j_chat.controller;

import com.example.job4j_chat.modal.Room;
import com.example.job4j_chat.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/room")
public class RoomController {
    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable Integer id) {
        var room = roomRepository.findById(id);
        return new ResponseEntity<>(room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return new ResponseEntity<>(roomRepository.save(room), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        if (room.getId() == null) {
            throw new NullPointerException("Id cannot be null");
        }
        roomRepository.save(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (id == null) {
            throw new NullPointerException("Id cannot be null");
        }
        roomRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
