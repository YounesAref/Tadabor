package com.younesaref.habittracker.controller;

import com.younesaref.habittracker.DTO.HabitResponse;
import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.service.HabitService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/{supabaseId}")
    public Habit save(@RequestBody Habit habit, @PathVariable UUID supabaseId) {
        return habitService.saveHabit(habit, supabaseId);
    }

    @GetMapping
    public List<HabitResponse> findAll(@RequestParam UUID supabaseId) {
        return habitService.findBySupabaseId(supabaseId)
                .stream()
                .map(HabitResponse::from)
                .toList();
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<Void> delete(@RequestParam UUID supabaseId,  @PathVariable Long habitId) {
        Habit habit = habitService.findById(habitId);

        if (habit == null) {
            return ResponseEntity.notFound().build();
        }

        if(!habit.getUser().getSupabaseId().equals(supabaseId)){
            return ResponseEntity.status(403).build();
        }

        habitService.deleteById(habitId);

        return ResponseEntity.ok().build();
    }
}


