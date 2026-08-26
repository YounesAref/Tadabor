package com.younesaref.habittracker.repository;

import com.younesaref.habittracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySupabaseId(UUID supabaseId);

}
