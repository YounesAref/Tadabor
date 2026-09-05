package com.younesaref.prayerapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timings {

    @JsonProperty("Fajr")
    private String fajr;

    @JsonProperty("Sunrise")
    private String sunrise;

    @JsonProperty("Dhuhr")
    private String dhuhr;

    @JsonProperty("Asr")
    private String asr;

    @JsonProperty("Maghrib")
    private String maghrib;

    @JsonProperty("Isha")
    private String isha;

    // Getters and setters
    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public void setDhuhr(String dhuhr) {
        this.dhuhr = dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
}
