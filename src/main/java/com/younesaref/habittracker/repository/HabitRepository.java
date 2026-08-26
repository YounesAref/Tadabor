package com.younesaref.habittracker.repository;

import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByUserAndActive(User user, boolean active);

    List<Habit> findByUser_SupabaseId(UUID userId);

    List<Habit> findByUserAndCategory(User user, String category);

    void deleteById(Long id);
}
