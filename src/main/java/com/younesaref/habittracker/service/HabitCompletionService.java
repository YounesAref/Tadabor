package com.younesaref.habittracker.service;

import com.younesaref.habittracker.DTO.HabitCompletionHistoryResponse;
import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.HabitCompletion;
import com.younesaref.habittracker.repository.HabitCompletionRepository;
import com.younesaref.habittracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitCompletionService {

    private final HabitCompletionRepository habitCompletionRepository;
    private final HabitService habitService;

    public HabitCompletionService(HabitCompletionRepository habitCompletionRepository, HabitService habitService) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.habitService = habitService;
    }

    public void save(HabitCompletion habitCompletion) {
        habitCompletionRepository.save(habitCompletion);
    }

    public HabitCompletion completedToday(Habit habit) {
        Optional<HabitCompletion> isCompletedToday = habitCompletionRepository.findByHabitAndCompletedOn(habit, LocalDate.now());

        if(isCompletedToday.isPresent()) {
            return isCompletedToday.get();
        }

        HabitCompletion habitCompletion = new HabitCompletion(habit, LocalDate.now());
        return habitCompletionRepository.save(habitCompletion);
    }

    public void uncomplete(Habit habit, LocalDate completedOn) {
        habitCompletionRepository
                .findByHabitAndCompletedOn(habit, completedOn)
                .ifPresent(habitCompletionRepository::delete);
    }

    public boolean isCompleted(Habit habit, LocalDate completedOn) {
        return habitCompletionRepository
                .findByHabitAndCompletedOn(habit, completedOn)
                .isPresent();
    }

    public List<HabitCompletionHistoryResponse> getAllCompletions(UUID supabaseId, LocalDate startDate, LocalDate endDate) {
        List<HabitCompletionHistoryResponse> habitCompletionHistories = new ArrayList<>();
        List<Habit> habits = habitService.findBySupabaseId(supabaseId);

        for  (Habit habit : habits) {
            List<LocalDate> completedDates = habitCompletionRepository
                    .findByHabitAndCompletedOnBetween(habit, startDate, endDate)
                    .stream()
                    .map(HabitCompletion::getCompletedOn)
                    .toList();

            habitCompletionHistories.add(new HabitCompletionHistoryResponse(
                    habit.getId(),
                    completedDates
            ));
        }

        return habitCompletionHistories;
    }

    public List<HabitCompletion> findByHabit(Habit habit) {
        return habitCompletionRepository.findByHabit(habit);
    }

    public List<HabitCompletion> findByHabitAndCompletedOnBetween(Habit habit, LocalDate startDate, LocalDate endDate) {
        return habitCompletionRepository.findByHabitAndCompletedOnBetween(habit, startDate, endDate);
    }

    public List<HabitCompletion> findByHabitOrderByCompletedOnDesc(Habit habit) {
        return habitCompletionRepository.findByHabitOrderByCompletedOnDesc(habit);
    }

    public Optional<HabitCompletion> findByHabitAndCompletedOn(Habit habit, LocalDate completedOn) {
        return habitCompletionRepository.findByHabitAndCompletedOn(habit, completedOn);
    }
}
