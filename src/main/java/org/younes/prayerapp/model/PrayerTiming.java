package org.younes.prayerapp.model;

import java.time.LocalDate;
import java.util.List;

public class PrayerTiming {

    private LocalDate date;
    private List<Prayer> prayers;

    public PrayerTiming(LocalDate date, List<Prayer> prayers) {
        this.date = date;
        this.prayers = prayers;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Prayer> getPrayers() {
        return prayers;
    }

    public void setPrayers(List<Prayer> prayers) {
        this.prayers = prayers;
    }

}
