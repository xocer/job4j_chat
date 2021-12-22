package com.example.job4j_chat.modal;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @ManyToOne
    private Person author;
}
