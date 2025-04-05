package com.liubomur.bot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @Column(nullable = false)
    private String currentCommand;

    @Column
    private String commandState;

    public User(Long id, String currentCommand) {
        this.id = id;
        this.currentCommand = currentCommand;
    }
}