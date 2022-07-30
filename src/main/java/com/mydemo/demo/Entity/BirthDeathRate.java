package com.mydemo.demo.Entity;

public class BirthDeathRate {
    private int id;

    private String period;

    private String birth_or_death;

    private String region;

    private int count;

    public BirthDeathRate() {
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

    public String getBirth_or_death() {
        return birth_or_death;
    }

    public void setBirth_or_death(String birth_or_death) {
        this.birth_or_death = birth_or_death;
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
                ", birth_or_death='" + birth_or_death + '\'' +
                ", region='" + region + '\'' +
                ", count=" + count +
                '}';
    }
}

