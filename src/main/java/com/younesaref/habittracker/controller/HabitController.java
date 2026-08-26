package com.younesaref.habittracker.controller;

import com.younesaref.habittracker.DTO.HabitResponse;
import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.service.HabitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public Habit save(@RequestBody Habit habit) {
        return habitService.saveHabit(habit);
    }

    @GetMapping
    public List<HabitResponse> findAll(@RequestParam UUID supabaseId) {
        return habitService.findBySupabaseId(supabaseId)
                .stream()
                .map(HabitResponse::from)
                .toList();
    }
}
