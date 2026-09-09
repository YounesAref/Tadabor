package com.younesaref.dailyreflection.repository;

import com.younesaref.dailyreflection.model.DailyReflection;
import com.younesaref.habittracker.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyReflectionRepository extends JpaRepository<DailyReflection, Long> {

    Optional<DailyReflection> findByDate(LocalDate date);


}
