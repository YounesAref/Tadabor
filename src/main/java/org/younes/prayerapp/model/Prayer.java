package org.younes.prayerapp.model;

import java.time.LocalTime;

public class Prayer {

    private String name;
    private LocalTime athan;
    private LocalTime iqamah;

    public Prayer(String name, LocalTime athan, LocalTime iqamah) {
        this.name = name;
        this.athan = athan;
        this.iqamah = iqamah;
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

    public LocalTime getIqamah() {
        return iqamah;
    }

    public void setIqamah(LocalTime iqamah) {
        this.iqamah = iqamah;
    }
}
