package com.younesaref.habittracker.DTO;

import com.younesaref.habittracker.entity.Habit;

import java.time.LocalDate;

public record HabitResponse(
        Long id,
        String name,
        String description,
        String color,
        String category,
        String frequency,
        boolean active,
        LocalDate createdAt
) {

    public static HabitResponse from(Habit habit) {
        return new HabitResponse(
                habit.getId(),
                habit.getName(),
                habit.getDescription(),
                habit.getColor(),
                habit.getCategory(),
                habit.getFrequency(),
                habit.isActive(),
                habit.getCreatedAt()
        );
    }
}
