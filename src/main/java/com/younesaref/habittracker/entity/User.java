package com.younesaref.habittracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supabaseId;
    private String username;
    private String email;
    private LocalDate createdAt;


    public User() {
    }

    public User(String supabaseId, String username, String email) {
        this.supabaseId = supabaseId;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDate.now();
    }
}
