package com.younesaref.dailyreflection.controller;

import com.younesaref.dailyreflection.model.DailyReflection;
import com.younesaref.dailyreflection.service.DailyReflectionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/daily-reflection")
public class DailyReflectionController {

    private DailyReflectionService dailyReflectionService;

    public DailyReflectionController(DailyReflectionService dailyReflectionService) {
        this.dailyReflectionService = dailyReflectionService;
    }

    @GetMapping("/{date}")
    public DailyReflection getDailyReflection(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate date) {
        return dailyReflectionService.findByDate(date)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Daily reflection not found"
                ));
    }

    @PutMapping("/{date}")
    public void addDailyReflection(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestBody DailyReflection request) {
        if (request == null) {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");}

        dailyReflectionService.save(request);
    }
}
