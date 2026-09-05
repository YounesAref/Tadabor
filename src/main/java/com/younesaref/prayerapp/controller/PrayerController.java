package com.younesaref.prayerapp.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.younesaref.prayerapp.model.PrayerTiming;
import com.younesaref.prayerapp.service.PrayerApiService;

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


    @RequestMapping("/prayerTimes/{date}/{longitude}/{latitude}")
    public PrayerTiming getPrayerTiming(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                        @PathVariable String longitude,
                                        @PathVariable String latitude) {
        return prayerApiService.fetchPrayerTimes(date, longitude, latitude);
    }

    @GetMapping
    public List<PrayerTiming> getPrayerTimingsBetween(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate,

            @RequestParam
            String longitude,
            @RequestParam
            String latitude
    ) {

        if(endDate.isBefore(startDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "End date must be after start date"
            );
        }

        List<PrayerTiming> prayerTimings = new ArrayList<>();

        for(LocalDate start = startDate; start.isBefore(endDate) || start.isEqual(endDate); start = start.plusDays(1)) {
            prayerTimings.add(prayerApiService.fetchPrayerTimes(start, longitude, latitude));
        }

        return prayerTimings;
    }
}
