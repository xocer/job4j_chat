package com.example.job4j_chat.modal;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "username must be not empty")
    private String username;
    @NotBlank(message = "password must be not empty")
    @NotNull(message = "password must be not null")
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
