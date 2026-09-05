package com.younesaref.dailyreflection.service;

import com.younesaref.dailyreflection.model.DailyReflection;
import com.younesaref.dailyreflection.repository.DailyReflectionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyReflectionService {

    private DailyReflectionRepository dailyReflectionRepository;

    public DailyReflectionService(DailyReflectionRepository dailyReflectionRepository) {
         this.dailyReflectionRepository = dailyReflectionRepository;
    }

    public Optional<DailyReflection> findByDate(LocalDate date) {
        return Optional.ofNullable(dailyReflectionRepository.findByDate(date));
    }

    public void save(DailyReflection dailyReflection) {
        dailyReflectionRepository.save(dailyReflection);
    }
}
