package com.younesaref.habittracker.repository;

import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {

    List<HabitCompletion> findByHabit(Habit habit);

    List<HabitCompletion> findByHabitAndCompletedOnBetween(Habit habit, LocalDate startDate, LocalDate endDate);

    List<HabitCompletion> findByHabitOrderByCompletedOnDesc(Habit habit);

    Optional<HabitCompletion> findByHabitAndCompletedOn(Habit habit, LocalDate completedOn);
}
