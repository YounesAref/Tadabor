package com.younesaref.habittracker.service;

import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.User;
import com.younesaref.habittracker.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public Habit findById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }

    public List<Habit> findByUser(User user) {
        return habitRepository.findByUser(user);
    }

    public List<Habit> findByUserAndActive(User user, boolean active) {
        return habitRepository.findByUserAndActive(user, active);
    }

    public List<Habit> findByUserAndCategory(User user, String category) {
        return habitRepository.findByUserAndCategory(user, category);
    }

    public void delete(Habit habit) {
        habitRepository.delete(habit);
    }

    public void deleteById(Long id) {
        habitRepository.deleteById(id);
    }

}
