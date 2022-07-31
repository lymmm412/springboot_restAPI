package com.mydemo.demo.Entity;

public class BirthDeathRate {
    private int id;

    private String period;

    private String birthOrDeath;

    private String region;

    private int count;

    public BirthDeathRate() {
    }

    public BirthDeathRate(String period, String birthOrDeath, String region, int count) {
        this.period = period;
        this.birthOrDeath = birthOrDeath;
        this.region = region;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBirthOrDeath() {
        return birthOrDeath;
    }

    public void setBirthOrDeath(String birthOrDeath) {
        this.birthOrDeath = birthOrDeath;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BirthDeathRate{" +
                "id=" + id +
                ", period='" + period + '\'' +
                ", birthOrDeath='" + birthOrDeath + '\'' +
                ", region='" + region + '\'' +
                ", count=" + count +
                '}';
    }
}

