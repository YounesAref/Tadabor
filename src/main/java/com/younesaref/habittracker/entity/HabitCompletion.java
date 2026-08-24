package com.younesaref.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"habit_id", "completed_on"})
        }
)

@Getter
public class HabitCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @Column(name = "completed_on")
    private LocalDate completedOn;

    public HabitCompletion() {
    }

    public HabitCompletion(Habit habit) {
        this.habit = habit;
        this.completedOn = LocalDate.now();
    }
}
