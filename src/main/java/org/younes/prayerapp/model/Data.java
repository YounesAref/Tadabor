package org.younes.prayerapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private Timings timings;

    @JsonProperty("date")
    private PrayerDate prayerDate;

    public Timings getTimings() {
        return timings;
    }

    public void setTimings(Timings timings) {
        this.timings = timings;
    }

    public PrayerDate getDate() {
        return prayerDate;
    }

    public void setDate(PrayerDate prayerDate) {
        this.prayerDate = prayerDate;
    }
}
