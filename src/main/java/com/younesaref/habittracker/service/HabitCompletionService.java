package com.younesaref.habittracker.service;

import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.HabitCompletion;
import com.younesaref.habittracker.repository.HabitCompletionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitCompletionService {

    private final HabitCompletionRepository habitCompletionRepository;

    public HabitCompletionService(HabitCompletionRepository habitCompletionRepository) {
        this.habitCompletionRepository = habitCompletionRepository;
    }

    public void save(HabitCompletion habitCompletion) {
        habitCompletionRepository.save(habitCompletion);
    }

    public HabitCompletion completedToday(Habit habit) {
        Optional<HabitCompletion> isCompletedToday = habitCompletionRepository.findByHabitAndCompletedOn(habit, LocalDate.now());

        if(isCompletedToday.isPresent()) {
            return isCompletedToday.get();
        }

        HabitCompletion habitCompletion = new HabitCompletion(habit);
        return habitCompletionRepository.save(habitCompletion);
    }

    public void uncompleteToday(Habit habit){
        habitCompletionRepository
                .findByHabitAndCompletedOn(habit, LocalDate.now())
                .ifPresent(habitCompletionRepository::delete);
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
