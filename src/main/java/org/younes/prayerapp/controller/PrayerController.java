package org.younes.prayerapp.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.younes.prayerapp.model.PrayerTiming;
import org.younes.prayerapp.service.PrayerApiService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/prayers")
public class PrayerController {

    private final PrayerApiService prayerApiService;

    public PrayerController(PrayerApiService prayerApiService) {
        this.prayerApiService = prayerApiService;
    }

    @RequestMapping("/{date}")
    public PrayerTiming getPrayerTiming(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return prayerApiService.fetchPrayerTimes(date);
    }

    @GetMapping
    public List<PrayerTiming> getPrayerTimingsBetween(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {

        if(endDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "End date must be after start date"
            );
        }

        List<PrayerTiming> prayerTimings = new ArrayList<>();

        for(LocalDate start = startDate; start.isBefore(endDate) || start.isEqual(endDate); start = start.plusDays(1)) {
            prayerTimings.add(prayerApiService.fetchPrayerTimes(start));
        }

        return prayerTimings;
    }
}
