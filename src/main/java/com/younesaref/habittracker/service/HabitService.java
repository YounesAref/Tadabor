package com.younesaref.habittracker.service;

import com.younesaref.habittracker.entity.Habit;
import com.younesaref.habittracker.entity.User;
import com.younesaref.habittracker.repository.HabitRepository;
import com.younesaref.habittracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public Habit saveHabit(Habit habit, UUID supabaseId) {
        User user = userRepository.findBySupabaseId(supabaseId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        habit.setUser(user);
        return habitRepository.save(habit);
    }

    public Habit findById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }

    public List<Habit> findBySupabaseId(UUID supabaseId) {
        return habitRepository.findByUser_SupabaseId(supabaseId);
    }

    public Optional<Habit> findById_supabaseId(Long habitId, UUID supabaseId) {
        User user = userRepository.findBySupabaseId(supabaseId).orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit = habitRepository.findById(habitId).orElse(null);

        if(habit == null || !habit.getUser().getSupabaseId().equals(supabaseId)) {return Optional.empty();}

        return Optional.of(habit);
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
