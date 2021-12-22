package com.example.job4j_chat.modal;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
