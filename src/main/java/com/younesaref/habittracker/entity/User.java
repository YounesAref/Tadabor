package com.younesaref.habittracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID supabaseId;
    private String username;
    private String email;
    private LocalDate createdAt;


    public User() {
    }

    public User(UUID supabaseId, String username, String email) {
        this.supabaseId = supabaseId;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDate.now();
    }
}
