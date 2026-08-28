package com.younesaref.habittracker.DTO;

public record UpdateHabitResponse(
        String name,
        String description,
        String color,
        String category,
        String frequency,
        Boolean active
) {
}
