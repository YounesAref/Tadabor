package com.younesaref.dailyreflection.controller;

import com.younesaref.dailyreflection.model.DailyReflection;
import com.younesaref.dailyreflection.service.DailyReflectionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@RestController("/api/daily-reflection")
public class DailyReflectionController {

    private DailyReflectionService dailyReflectionService;

    public DailyReflectionController(DailyReflectionService dailyReflectionService) {
        this.dailyReflectionService = dailyReflectionService;
    }

    @GetMapping
    public DailyReflection getDailyReflection(@PathVariable LocalDate date) {
        return dailyReflectionService.findByDate(date)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Daily reflection not found"
                ));
    }

    @PutMapping
    public void addDailyReflection(@RequestBody DailyReflection dailyReflection) {}
}
