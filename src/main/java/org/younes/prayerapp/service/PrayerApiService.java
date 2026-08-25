package org.younes.prayerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.younes.prayerapp.model.PrayerTiming;
import org.younes.prayerapp.model.PrayerTimingResponse;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class PrayerApiService {

    private final HashMap<LocalDate, PrayerTiming> prayerTimingsByDateMap = new HashMap<>();
    private final RestTemplate restTemplate;
    private final PrayerConversionService prayerConversionService;
    
    public PrayerApiService(RestTemplate restTemplate, PrayerConversionService prayerConversionService) {
        this.restTemplate = restTemplate;
        this.prayerConversionService = prayerConversionService;
    }

    public PrayerTiming fetchPrayerTimes(LocalDate date, String longitude, String latitude) {

        String formattedDate = date.format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        System.out.println("A request has been made!");

        if(prayerTimingsByDateMap.containsKey(date)) return prayerTimingsByDateMap.get(date);

        String url = String.format("https://api.aladhan.com/v1/timings/%s?latitude=%s&longitude=%s&method=2",
                formattedDate,
                latitude,
                longitude);

        PrayerTimingResponse response = restTemplate.getForObject(url, PrayerTimingResponse.class);

        String r = restTemplate.getForObject(url, String.class);
        System.out.println(r);

        PrayerTiming prayerTiming = prayerConversionService.convertPrayerTiming(response);

        prayerTimingsByDateMap.put(date, prayerTiming);

        return prayerTiming;
    }

    private void cleanCache(LocalDate date) {
        prayerTimingsByDateMap.remove(date);
    }


}