package com.younesaref.prayerapp.model;

import java.time.LocalTime;

public class Prayer {

    private String name;
    private LocalTime athan;

    public Prayer(String name, LocalTime athan) {
        this.name = name;
        this.athan = athan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getAthan() {
        return athan;
    }

    public void setAthan(LocalTime athan) {
        this.athan = athan;
    }
    
}
