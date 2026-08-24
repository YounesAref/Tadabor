package org.younes.prayerapp.service;

import org.springframework.stereotype.Service;
import org.younes.prayerapp.model.Prayer;
import org.younes.prayerapp.model.PrayerTiming;
import org.younes.prayerapp.model.PrayerTimingResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrayerConversionService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public PrayerTiming convertPrayerTiming(PrayerTimingResponse response) {
        if (response.getData() == null || response.getData().getTimings() == null) {
            throw new IllegalStateException("Invalid response from Aladhan API");
        }

        return new PrayerTiming(getDate(response),
                createPrayerList(response));
    }

    private LocalDate getDate(PrayerTimingResponse response) {
        return LocalDate.parse(response.getData().getDate().getGregorian().getDate(), dateFormatter);
    }


    private List<Prayer> createPrayerList(PrayerTimingResponse response) {

        Prayer fajr = new Prayer("Fajr",
                convertToLocalTime(response.getData().getTimings().getFajr()),
                addIqamah(response.getData().getTimings().getFajr(), 20));
        Prayer dhuhr = new Prayer("Dhuhr",
                convertToLocalTime(response.getData().getTimings().getDhuhr()),
                addIqamah(response.getData().getTimings().getDhuhr(), 20));
        Prayer asr = new Prayer("Asr",
                convertToLocalTime(response.getData().getTimings().getAsr()),
                addIqamah(response.getData().getTimings().getAsr(), 20));
        Prayer maghrib = new Prayer("Maghrib",
                convertToLocalTime(response.getData().getTimings().getMaghrib()),
                addIqamah(response.getData().getTimings().getMaghrib(), 5));
        Prayer isha = new Prayer("Isha",
                convertToLocalTime(response.getData().getTimings().getIsha()),
                addIqamah(response.getData().getTimings().getIsha(), 20));

        return List.of(fajr, dhuhr, asr, maghrib, isha);
    }

    private LocalTime convertToLocalTime(String time) {
        if (time == null) {
            throw new IllegalArgumentException("Prayer time string is null");
        }

        return LocalTime.parse(time, formatter);
    }

    private LocalTime addIqamah(String time, int iqamah) {
        return LocalTime.parse(time, formatter).plusMinutes(iqamah);
    }
}
