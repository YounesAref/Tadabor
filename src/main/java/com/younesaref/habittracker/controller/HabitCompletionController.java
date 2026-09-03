package com.younesaref.habittracker.controller;

import com.younesaref.habittracker.DTO.HabitCompletionHistoryResponse;
import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.HabitCompletion;
import com.younesaref.habittracker.service.HabitCompletionService;
import com.younesaref.habittracker.service.HabitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/completions")
public class HabitCompletionController {

    private final HabitCompletionService habitCompletionService;
    private final HabitService habitService;

    public HabitCompletionController(HabitCompletionService habitCompletionService, HabitService habitService) {
        this.habitCompletionService = habitCompletionService;
        this.habitService = habitService;
    }

    @GetMapping
    public List<HabitCompletionHistoryResponse> getAllCompletions(
            @RequestParam UUID supabaseId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {

        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "End date cannot be before start date"
            );
        }

        return habitCompletionService.getAllCompletions(supabaseId, startDate, endDate);
    }

    @PutMapping("/{habitId}")
    public void completeHabit(@PathVariable Long habitId,
                              @RequestParam UUID supabaseId,
                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  LocalDate completedOn
    ) {
        if (completedOn.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Completed date cannot be in the future"
            );
        }
        Habit habit = habitService.findById(habitId);

        if (habit == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Habit not found"
            );
        }

        if(!habit.getUser().getSupabaseId().equals(supabaseId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to complete this habit"
            );
        }

        if (!habit.isActive()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot complete an inactive habit"
            );
        }

        if(habitCompletionService.isCompleted(habit, completedOn)) {return;}

        HabitCompletion habitCompletion = new HabitCompletion(habit, completedOn);

        habitCompletionService.save(habitCompletion);
    }

    @DeleteMapping("/{habitId}")
    public void uncompleteHabit(@PathVariable Long habitId,
                                @RequestParam UUID supabaseId,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                    LocalDate completedOn
                                ) {
        Habit habit = habitService.findById(habitId);

        if (habit == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Habit not found"
            );
        }

        if(!habit.getUser().getSupabaseId().equals(supabaseId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You are not allowed to uncomplete this habit"
            );
        }

        if(habitCompletionService.isCompleted(habit, completedOn)) {
            habitCompletionService.uncomplete(habit, completedOn);
        }
    }
}
