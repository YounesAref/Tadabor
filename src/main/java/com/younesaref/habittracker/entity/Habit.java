package com.younesaref.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;
    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private String color;

    @Setter
    private String category;

    @Setter
    private String frequency;

    final private LocalDate createdAt = LocalDate.now();

    @Setter
    private boolean active;

    public Habit() {
    }

    public Habit(User user,
                 String name,
                 String description,
                 String color,
                 String category,
                 String frequency) {

        this.user = user;
        this.name = name;
        this.description = description;
        this.color = color;
        this.category = category;
        this.frequency = frequency;
        this.active = true;

    }
}
