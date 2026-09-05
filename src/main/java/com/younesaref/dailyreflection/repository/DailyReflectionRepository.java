package com.younesaref.dailyreflection.repository;

import com.younesaref.dailyreflection.model.DailyReflection;
import com.younesaref.habittracker.entity.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DailyReflectionRepository extends JpaRepository<DailyReflection, Long> {

    DailyReflection findByDate(LocalDate date);


}
